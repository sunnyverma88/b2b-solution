package com.techieonthenet.controller;

import com.techieonthenet.dto.OrderDto;
import com.techieonthenet.dto.ShippingAddressDto;
import com.techieonthenet.entity.*;
import com.techieonthenet.entity.common.TaskStatus;
import com.techieonthenet.entity.common.TaskType;
import com.techieonthenet.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order controller.
 */
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

    @Autowired
    ModelMapper modelMapper;


    /**
     * Gets order details.
     *
     * @param model     the model
     * @param principal the principal
     * @param message   the message
     * @return the order details
     */
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

    /**
     * Save order redirect view.
     *
     * @param dto                the dto
     * @param redirectAttributes the redirect attributes
     * @param session            the session
     * @return the redirect view
     */
    @PostMapping("/save")
    public RedirectView saveOrder(@ModelAttribute ShippingAddressDto dto, RedirectAttributes redirectAttributes, HttpSession session) {
        String url = "";
        try {
            User user = (User) session.getAttribute("user");

            ShoppingCart cart = shoppingCartService.findByUserId(user.getId());
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(cart);
            Order order = orderService.createOrder(cartItemList, dto, user, cart);
            cart = shoppingCartService.clearShoppingCart(cart);
            session.setAttribute("cartSize", 0);
            session.setAttribute("tasks", taskService.findByUserAndTaskStatus(user, TaskStatus.PENDING_APPROVAL));
            emailService.sendOrderConfirmationEmail(order , getApprovalUserName(order, TaskType.ORDER_APPROVAL_LEVEL_1) ,
                    getApprovalUserName(order, TaskType.ORDER_APPROVAL_LEVEL_2));
            url = "/order/po/" + order.getId() + "";
        } catch (Exception e) {
            logger.error("Error Occurred : {}", e.getMessage());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            url = "/order/details";
        }
        return new RedirectView(url);
    }

    /**
     * Gets order confirmation.
     *
     * @param model     the model
     * @param principal the principal
     * @param orderId   the order id
     * @return the order confirmation
     */
    @GetMapping("/po/{orderId}")
    public String getOrderConfirmation(Model model, Principal principal, @PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "order-po";
    }

    /**
     * Gets order details.
     *
     * @param model     the model
     * @param principal the principal
     * @param orderId   the order id
     * @return the order details
     */
    @GetMapping("/details/{orderId}")
    public String getOrderDetails(Model model, Principal principal, @PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "order-details";
    }

    @GetMapping("/edit/{orderId}")
    public String editOrderDetails(Model model, Principal principal, @PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        OrderDto orderDto = modelMapper.map(order , OrderDto.class);
        model.addAttribute("orderDto", orderDto);
        return "edit-order";
    }

    @PostMapping("/update")
    public RedirectView updateOrder(@ModelAttribute OrderDto orderDto, RedirectAttributes redirectAttributes, HttpSession session) {
        String redirectUrl;
        User user = (User) session.getAttribute("user");
        Order order= modelMapper.map(orderDto , Order.class);
        OrderComment comment = new OrderComment();
        comment.setDescription(orderDto.getComment());
        comment.setOrder(order);
        comment.setUser(user);
        List<OrderComment> orderComments=new ArrayList<>();
        orderComments.add(comment);
        order.setOrderComments(orderComments);
       try {
           orderService.updateOrder(order,user);
           redirectUrl = "/order/details/" + order.getId();
       }catch(Exception e) {
            redirectAttributes.addFlashAttribute("message", "Something Went Wrong !! " + e.getLocalizedMessage());
            redirectUrl = "/order/history/admin";
        }
        return new RedirectView(redirectUrl);
    }

    /**
     * Gets order history for user.
     *
     * @param model   the model
     * @param session the session
     * @return the order history for user
     */
    @GetMapping("/history")
    public String getOrderHistoryForUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findByUser(user);
        model.addAttribute("orders", orders);
        return "order-history";
    }

    /**
     * Gets order history for group.
     *
     * @param model   the model
     * @param session the session
     * @return the order history for group
     */
    @GetMapping("/history/group")
    public String getOrderHistoryForGroup(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findByGroup(user.getGroup());
        model.addAttribute("orders", orders);
        return "order-history";
    }

    @GetMapping("/history/admin")
    public String getOrderHistoryForAdmin(Model model,@RequestParam(name = "message", required = false) String message) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        Iterable<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin-order-history";
    }

    private String getApprovalUserName(Order order, TaskType taskType) {
        String taskUser = "";
        for (TaskItem taskItem : order.getTaskItems()) {
            if (taskItem.getTaskType().equals(taskType)) {
                taskUser = taskItem.getUsers().stream().findFirst().get().getFirstName().concat(" ").concat(taskItem.getUsers().stream().findFirst().get().getLastName());
                break;
            }
        }
        return taskUser;
    }

}
