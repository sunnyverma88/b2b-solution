package com.techieonthenet.controller;

import com.techieonthenet.dto.ShippingAddressDto;
import com.techieonthenet.entity.Address;
import com.techieonthenet.entity.User;
import com.techieonthenet.service.GroupService;
import com.techieonthenet.service.ShoppingCartService;
import com.techieonthenet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

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

    @GetMapping("/details")
    public String getOrderDetails(Model model , Principal pricipal ) {
        User user = userService.findByUsernameAndEnabled(pricipal.getName());
        model.addAttribute("cart" , shoppingCartService.findByUserId(user.getId()));
        Address billingAddress = groupService.findById(user.getGroup().getId()).getAddress();
        logger.info("Billing Address Name  : {} " , billingAddress.getName());
        model.addAttribute("billingAddress" , billingAddress);
        ShippingAddressDto  dto = new ShippingAddressDto();
        dto.setAddressline1(billingAddress.getAddressline1());
        dto.setAddressline2(billingAddress.getAddressline2());
        dto.setCity(billingAddress.getCity());
        dto.setState(billingAddress.getState());
        dto.setZipcode(billingAddress.getZipcode());
        model.addAttribute("shippingAddress" , dto);
        return "order";
    }

    @PostMapping("/save")
    public void saveOrder(@ModelAttribute ShippingAddressDto dto , RedirectAttributes redirectAttributes)
    {

    }

    @GetMapping("/confirmation")
    public String getOrderConfirmation()
    {
        return "order-confirm";
    }
}
