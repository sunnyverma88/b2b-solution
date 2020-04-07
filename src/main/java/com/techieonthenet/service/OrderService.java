package com.techieonthenet.service;

import com.techieonthenet.dto.ShippingAddressDto;
import com.techieonthenet.entity.*;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<Order> findAll();

    /**
     * Save order.
     *
     * @param order the order
     * @return the order
     */
    Order save(Order order);

    /**
     * Find by id order.
     *
     * @param orderId the order id
     * @return the order
     */
    Order findById(Long orderId);

    /**
     * Find by user list.
     *
     * @param user the user
     * @return the list
     */
    List<Order> findByUser(User user);

    /**
     * Create order order.
     *
     * @param cartItemList the cart item list
     * @param dto          the dto
     * @param user         the user
     * @param cart         the cart
     * @return the order
     */
    Order createOrder(List<CartItem> cartItemList, ShippingAddressDto dto, User user, ShoppingCart cart);

    /**
     * Find by group list.
     *
     * @param group the group
     * @return the list
     */
    List<Order> findByGroup(Group group);

    Order updateOrder(Order order , User user);
}
