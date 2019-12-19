package com.techieonthenet.controller;

import com.techieonthenet.dto.ShoppingCartDto;
import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.Product;
import com.techieonthenet.entity.ShoppingCart;
import com.techieonthenet.entity.User;
import com.techieonthenet.service.CartItemService;
import com.techieonthenet.service.ProductService;
import com.techieonthenet.service.ShoppingCartService;
import com.techieonthenet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;

/**
 * The type Cart controller.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private static final String CART_SIZE = "cartSize";
    private static Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private ProductService ps;
    @Autowired
    private UserService us;
    @Autowired
    private CartItemService cis;
    @Autowired
    private ShoppingCartService scs;

    /**
     * Add redirect view.
     *
     * @param productId the product id
     * @param principal the principal
     * @param session   the session
     * @return the redirect view
     */
    @GetMapping(value = "/add/{pid}")
    public RedirectView add(@PathVariable(name = "pid") Long productId, Principal principal, HttpSession session) {

        Product product = ps.findById(productId);
        User user = (User) session.getAttribute("user");
        ShoppingCart cart = scs.findByUserId(user.getId());
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
            scs.save(cart);
        }
        cis.addProductToCartItem(product, cart, 1);
        session.setAttribute(CART_SIZE, scs.findByUserId(user.getId()).getTotalItems());
        return new RedirectView("/cart/all");
    }

    /**
     * Add string.
     *
     * @param principal the principal
     * @param model     the model
     * @param session   the session
     * @return the string
     */
    @GetMapping(value = "/all")
    public String add(Principal principal, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ShoppingCart cart = scs.findByUserId(user.getId());

        ShoppingCartDto dto = new ShoppingCartDto();
        if (cart != null) {
            logger.info("Item Cart List - {}", cart.getCartItemList());
            dto.setCartItems(cart.getCartItemList());
            logger.info("Item Cart List - {}", dto.getCartItems());
        } else {
            cart = new ShoppingCart();
            cart.setUser(user);
            scs.save(cart);
        }
        model.addAttribute("cartDtoForm", dto);
        session.setAttribute(CART_SIZE, cart.getTotalItems());
        session.setAttribute("user" , user);
        model.addAttribute("cart", cart);
        return "cart";
    }

    /**
     * Update cart redirect view.
     *
     * @param cartDtoForm the cart dto form
     * @param principal   the principal
     * @param session     the session
     * @return the redirect view
     */
    @PostMapping("/update")
    public RedirectView updateCart(@ModelAttribute ShoppingCartDto cartDtoForm, Principal principal, HttpSession session) {
        logger.info("Shopping Cart item size - {}", cartDtoForm.getCartItems().size());

        cartDtoForm.getCartItems().forEach(cartItem -> {
            CartItem updatedItem = cis.findById(cartItem.getId());
            logger.info("Updated Quantity {}", cartItem.getQty());
            if (cartItem.getQty() == 0) {
                cis.delete(updatedItem);
            } else {
                updatedItem.setQty(cartItem.getQty());
                updatedItem.setSubTotal(updatedItem.getProduct().getMrpPrice().multiply(new BigDecimal(cartItem.getQty())));
                cis.save(updatedItem);
            }
        });
        ShoppingCart cart = scs.updateShoppingCart(scs.updateShoppingCart(scs.findByUserId(us.findByUsernameAndEnabled(principal.getName()).getId())));
        session.setAttribute(CART_SIZE, cart.getTotalItems());
        return new RedirectView("/cart/all");
    }
}
