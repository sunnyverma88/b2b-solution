package com.techieonthenet.service;

import com.techieonthenet.dto.ShippingAddressDto;
import com.techieonthenet.entity.*;

import java.util.List;

public interface OrderService {

    Iterable<Order> findAll();

    Order save(Order order);

    Order findById(Long orderId);

    List<Order> findByUser(User user);

    Order createOrder(List<CartItem> cartItemList, ShippingAddressDto dto, User user, ShoppingCart cart);

    List<Order> findByGroup(Group group);
}
