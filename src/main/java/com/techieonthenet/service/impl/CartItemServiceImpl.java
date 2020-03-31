package com.techieonthenet.service.impl;

import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.Product;
import com.techieonthenet.entity.ShoppingCart;
import com.techieonthenet.repository.CartItemRepository;
import com.techieonthenet.service.CartItemService;
import com.techieonthenet.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Cart item service.
 */
@Service
public class CartItemServiceImpl implements CartItemService {


    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @Override
    public Iterable<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public boolean addProductToCartItem(Product product, ShoppingCart cart, int qty) {

        List<CartItem> cartItemList = cart.getCartItemList();
        LocalDate estimatedDeliveryDate = LocalDate.now().plusDays(product.getShippingDays());
        if (!cartItemList.isEmpty() && cartItemList != null) {
            for (CartItem cartItem : cartItemList) {
                if (product.getId() == cartItem.getProduct().getId()) {
                    cartItem.setQty(cartItem.getQty() + qty);
                    cartItem.setSubTotal(product.getPriceWithoutGst().multiply(new BigDecimal(cartItem.getQty())));
                    cartItem.setGrandTotal(product.getSellingPrice().multiply(new BigDecimal(cartItem.getQty())));
                    cartItem.setDeliveryDate(estimatedDeliveryDate);
                    cartItem.setShoppingCart(cart);
                    cartItemRepository.save(cartItem);
                    shoppingCartService.updateShoppingCart(cart);
                    return true;
                }
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(cart);
        cartItem.setProduct(product);
        cartItem.setQty(qty);
        cartItem.setDeliveryDate(estimatedDeliveryDate);
        cartItem.setSubTotal(product.getPriceWithoutGst().multiply(new BigDecimal(qty)));
        cartItem.setGrandTotal(product.getSellingPrice().multiply(new BigDecimal(qty)));
        cartItem = cartItemRepository.save(cartItem);
        shoppingCartService.updateShoppingCart(cart);
        return true;
    }

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart cart) {
        return cartItemRepository.findByShoppingCart(cart);
    }

    public void delete(CartItem item) {
        cartItemRepository.deleteById(item.getId());
    }

    public CartItem update(CartItem cartItem)
    {
        CartItem updatedItem = cartItemRepository.findById(cartItem.getId()).get();
        updatedItem.setQty(cartItem.getQty());
        updatedItem.setSubTotal(updatedItem.getProduct().getPriceWithoutGst().multiply(new BigDecimal(cartItem.getQty())));
        updatedItem.setGrandTotal(updatedItem.getProduct().getSellingPrice().multiply(new BigDecimal(cartItem.getQty())));
        save(updatedItem);
        return updatedItem;
    }
}
