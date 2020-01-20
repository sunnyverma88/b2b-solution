package com.techieonthenet.controller;

import com.techieonthenet.dto.ShippingAddressDto;
import com.techieonthenet.entity.*;
import com.techieonthenet.entity.common.TaskStatus;
import com.techieonthenet.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/details")
    public String getOrderDetails(Model model, Principal principal, @RequestParam(name = "message", required = false) String message) {
        User user = userService.findByUsernameAndEnabled(principal.getName());
        model.addAttribute("cart", shoppingCartService.findByUserId(user.getId()));
        Address billingAddress = groupService.findById(user.getGroup().getId()).getAddress();
        logger.info("Billing Address Name  : {} ", billingAddress.getName());
        model.addAttribute("billingAddress", billingAddress);
        ShippingAddressDto dto = new ShippingAddressDto();
        dto.setAddressline1(billingAddress.getAddressline1());
        dto.setAddressline2(billingAddress.getAddressline2());
        dto.setCity(billingAddress.getCity());
        dto.setState(billingAddress.getState());
        dto.setZipcode(billingAddress.getZipcode());
        model.addAttribute("shippingAddress", dto);
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "order";
    }

    @PostMapping("/save")
    public RedirectView saveOrder(@ModelAttribute ShippingAddressDto dto, RedirectAttributes redirectAttributes, HttpSession session) {
        String url = "";
        try {
            User user = (User) session.getAttribute("user");

            ShoppingCart cart = shoppingCartService.findByUserId(user.getId());
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(cart);
            Order order = orderService.createOrder(cartItemList, dto, user, cart);
            cart = shoppingCartService.clearShoppingCart(cart);
            taskService.createApprovalTasks(user.getGroup(), order);
            session.setAttribute("cartSize", 0);
            session.setAttribute("tasks", taskService.findByUserAndTaskStatus(user, TaskStatus.PENDING_APPROVAL));
            sendOrderConfirmationEmail(order);
            url = "/order/" + order.getId() + "/po";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error Occurred : {}", e.getMessage());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            url = "/order/details";
        }
        return new RedirectView(url);
    }

    @GetMapping("/{orderId}/po")
    public String getOrderConfirmation(Model model, Principal principal, @PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "order-po";
    }

    @GetMapping("/{orderId}/details")
    public String getOrderDetails(Model model, Principal principal, @PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "order-details";
    }

    @GetMapping("/history")
    public String getOrderHistoryForUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findByUser(user);
        model.addAttribute("orders", orders);
        return "order-history";
    }

    @GetMapping("/history/group")
    public String getOrderHistoryForGroup(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findByGroup(user.getGroup());
        model.addAttribute("orders", orders);
        return "order-history";
    }

    private void sendOrderConfirmationEmail(Order order) throws IOException, MessagingException {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("order", order);
        emailService.sendSimpleMessage(order.getUser().getEmail(), "Thanks for your order - Apprize !!", valueMap, "order-details");
    }
}
