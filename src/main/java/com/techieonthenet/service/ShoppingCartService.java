package com.techieonthenet.service;

import com.techieonthenet.entity.ShoppingCart;

/**
 * The interface Shopping cart service.
 */
public interface ShoppingCartService {

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<ShoppingCart> findAll();

    /**
     * Save shopping cart.
     *
     * @param cart the cart
     * @return the shopping cart
     */
    ShoppingCart save(ShoppingCart cart);

    /**
     * Find by id shopping cart.
     *
     * @param id the id
     * @return the shopping cart
     */
    ShoppingCart findById(Long id);

    /**
     * Find by user id shopping cart.
     *
     * @param userId the user id
     * @return the shopping cart
     */
    ShoppingCart findByUserId(Long userId);

    /**
     * Update shopping cart shopping cart.
     *
     * @param shoppingCart the shopping cart
     * @return the shopping cart
     */
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    /**
     * Clear shopping cart shopping cart.
     *
     * @param shoppingCart the shopping cart
     * @return the shopping cart
     */
    ShoppingCart clearShoppingCart(ShoppingCart shoppingCart);

}
