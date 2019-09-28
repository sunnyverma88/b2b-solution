package com.techieonthenet.service;

import com.techieonthenet.entity.ShoppingCart;

public interface ShoppingCartService {

    public Iterable<ShoppingCart> findAll();

    public ShoppingCart save(ShoppingCart cart);

    public ShoppingCart findById(Long id);

    public ShoppingCart findByUserId(Long userId);

    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    public ShoppingCart clearShoppingCart(ShoppingCart shoppingCart);

}
