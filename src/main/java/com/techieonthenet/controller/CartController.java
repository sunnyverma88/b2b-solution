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
import java.util.List;

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
        return new RedirectView("/product/all");
    }

    /**
     * Gets all cart items.
     *
     * @param principal the principal
     * @param model     the model
     * @param session   the session
     * @return the all cart items
     */
    @GetMapping(value = "/all")
    public String getAllCartItems(Principal principal, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String message="";
        ShoppingCart cart = scs.findByUserId(user.getId());
        ShoppingCartDto dto = new ShoppingCartDto();
        if (cart != null) {
            logger.info("Item Cart List - {}", cart.getCartItemList());
            for(CartItem cartItem : cart.getCartItemList()) {
                if (!cartItem.getProduct().isVisible()) {
                    cartItem.setQty(0);
                    message="You Cart has been updated as one or more items were not available , Please select items again.";
                    cart.getCartItemList().remove(cartItem);
                    cis.delete(cartItem);
                }
            }
            logger.info("Item Cart List - {}", dto.getCartItems());
        } else {
            cart = new ShoppingCart();
            cart.setUser(user);
            scs.save(cart);
        }
        cart = scs.updateShoppingCart(scs.updateShoppingCart(scs.findByUserId(us.findByUsernameAndEnabled(principal.getName()).getId())));
        dto.setCartItems(cart.getCartItemList());
        model.addAttribute("cartDtoForm", dto);
        session.setAttribute(CART_SIZE, cart.getTotalItems());
        if (message != null) {
        model.addAttribute("message" , message);}
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
        session.setAttribute(CART_SIZE, updateCart(cartDtoForm.getCartItems(),principal.getName()).getTotalItems());
        return new RedirectView("/cart/all");
    }

    private ShoppingCart updateCart(List<CartItem> cartItems , String  username)
    {
        cartItems.forEach(cartItem -> {
            CartItem updatedItem = cis.findById(cartItem.getId());
            logger.info("Updated Quantity {}", cartItem.getQty());
            if (cartItem.getQty() == 0 ) {
                cis.delete(updatedItem);
            } else {
                updatedItem.setQty(cartItem.getQty());
                updatedItem.setSubTotal(updatedItem.getProduct().getPriceWithoutGst().multiply(new BigDecimal(cartItem.getQty())));
                updatedItem.setGrandTotal(updatedItem.getProduct().getSellingPrice().multiply(new BigDecimal(cartItem.getQty())));
                cis.save(updatedItem);
            }
        });
        return scs.updateShoppingCart(scs.findByUserId(us.findByUsernameAndEnabled(username).getId()));
    }
}
