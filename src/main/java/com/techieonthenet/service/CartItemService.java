package com.techieonthenet.service;


import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.Product;
import com.techieonthenet.entity.ShoppingCart;

import java.util.List;

/**
 * The interface Cart item service.
 */
public interface CartItemService {

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<CartItem> findAll();

    /**
     * Save cart item.
     *
     * @param cartItem the cart item
     * @return the cart item
     */
    CartItem save(CartItem cartItem);

    /**
     * Find by id cart item.
     *
     * @param id the id
     * @return the cart item
     */
    CartItem findById(Long id);

    /**
     * Add product to cart item boolean.
     *
     * @param product the product
     * @param cart    the cart
     * @param qty     the qty
     * @return the boolean
     */
    boolean addProductToCartItem(Product product, ShoppingCart cart, int qty);

    /**
     * Find by shopping cart list.
     *
     * @param cart the cart
     * @return the list
     */
    List<CartItem> findByShoppingCart(ShoppingCart cart);

    /**
     * Delete.
     *
     * @param item the item
     */
    void delete(CartItem item);

    CartItem update(CartItem cartItem);


}
