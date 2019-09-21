package com.techieonthenet.service;


import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.Product;
import com.techieonthenet.entity.ShoppingCart;

import java.util.List;

public interface CartItemService {

    Iterable<CartItem> findAll();

    CartItem save(CartItem cartItem);

    CartItem findById(Long id);

    boolean addProductToCartItem(Product product, ShoppingCart cart, int qty);

    List<CartItem> findByShoppingCart(ShoppingCart cart);

    void delete(CartItem item);


}
