package com.techieonthenet.controller;

import com.techieonthenet.dto.ShippingAddressDto;
import com.techieonthenet.entity.*;
import com.techieonthenet.entity.common.AddressType;
import com.techieonthenet.entity.common.OrderStatus;
import com.techieonthenet.service.*;
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
import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/details")
    public String getOrderDetails(Model model, Principal principal) {
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
        return "order";
    }

    @PostMapping("/save")
    public RedirectView saveOrder(@ModelAttribute ShippingAddressDto dto, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ShoppingCart cart = shoppingCartService.findByUserId(user.getId());
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(cart);
        Order order = createOrder(cartItemList, dto, user, cart);
        cart=shoppingCartService.clearShoppingCart(cart);
        session.setAttribute("cartSize" , cart.getTotalItems());
        String url = "/order/" + order.getId() + "/po";
        return new RedirectView(url);
    }

    @GetMapping("/{orderId}/po")
    public String getOrderConfirmation(Model model, Principal principal, @PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "order-po";
    }

    private Order createOrder(List<CartItem> cartItemList, ShippingAddressDto dto, User user, ShoppingCart cart) {
        Order order = new Order();
        for (CartItem cartItem : cartItemList) {
            cartItem.setOrder(order);
        }
        order.setCartItemList(cartItemList);
        Address address = new Address();
        address.setType(AddressType.SHIPPING_ADDRESS);
        address.setState(dto.getState());
        address.setZipcode(dto.getZipcode());
        address.setAddressline1(dto.getAddressline1());
        address.setAddressline2(dto.getAddressline2());
        address.setCity(dto.getCity());

        order.setAddress(address);
        order.setOrderStatus(OrderStatus.PENDING_APPROVAL);
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setGst(cart.getGst());
        order.setSubTotal(cart.getCartTotal());
        order.setOrderTotal(cart.getGrandTotal());
        return orderService.save(order);
    }
}
