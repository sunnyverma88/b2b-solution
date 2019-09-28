package com.techieonthenet.service;

import com.techieonthenet.entity.Order;

public interface OrderService {

    Iterable<Order> findAll();

    Order save(Order order);

    Order findById(Long orderId);

}
