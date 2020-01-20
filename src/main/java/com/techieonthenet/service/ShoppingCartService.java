package com.techieonthenet.service;

import com.techieonthenet.entity.ShoppingCart;

public interface ShoppingCartService {

    Iterable<ShoppingCart> findAll();

    ShoppingCart save(ShoppingCart cart);

    ShoppingCart findById(Long id);

    ShoppingCart findByUserId(Long userId);

    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart clearShoppingCart(ShoppingCart shoppingCart);


}
