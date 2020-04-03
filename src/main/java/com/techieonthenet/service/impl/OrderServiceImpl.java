package com.techieonthenet.service.impl;

import com.techieonthenet.dto.ShippingAddressDto;
import com.techieonthenet.entity.*;
import com.techieonthenet.entity.common.AddressType;
import com.techieonthenet.entity.common.OrderStatus;
import com.techieonthenet.exception.UserDefinedException;
import com.techieonthenet.repository.OrderRepository;
import com.techieonthenet.service.CartItemService;
import com.techieonthenet.service.OrderService;
import com.techieonthenet.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Order service.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    /**
     * The Task service.
     */
    @Autowired
    TaskService taskService;

    @Autowired
    CartItemService cartItemService;

    @Override
    public Iterable<Order> findAll() {
        Pageable paging =PageRequest.of(0,25,Sort.by("id").descending());
        return orderRepository.findAll(paging).getContent();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public List<Order> findByUser(User user) {
        Pageable paging = PageRequest.of(0, 25, Sort.by("id").descending());
        return orderRepository.findByUser(user, paging).getContent();
    }

    @Override
    @Transactional
    public Order createOrder(List<CartItem> cartItemList, ShippingAddressDto dto, User user, ShoppingCart cart) {
        Order order = new Order();
        for (CartItem cartItem : cartItemList) {
            cartItem.setOrder(order);
        }
        order.setCartItemList(cartItemList);
        Address address = new Address();
        address.setName(user.getGroup().getName());
        address.setType(AddressType.SHIPPING_ADDRESS);
        address.setState(dto.getState());
        address.setZipcode(dto.getZipcode());
        address.setAddressline1(dto.getAddressline1());
        address.setAddressline2(dto.getAddressline2());
        address.setCity(dto.getCity());
        order.setAddress(address);
        order.setOrderStatus(OrderStatus.PENDING_APPROVAL);
        order.setUser(user);
        order.setGroup(user.getGroup());
        order.setOrderDate(LocalDate.now());
        order.setGst(cart.getGst());
        order.setSubTotal(cart.getCartTotal());
        order.setOrderTotal(cart.getGrandTotal().add(order.getShippingCost()));
        save(order);
        taskService.createApprovalTasks(user.getGroup(), order);
        return order;
    }

    @Override
    public List<Order> findByGroup(Group group) {
        Pageable paging = PageRequest.of(0, 25, Sort.by("id").descending());
        return orderRepository.findByGroup(group, paging).getContent();
    }

    @Override
    public Order updateOrder(Order order){
        Order updatedOrder = orderRepository.findById(order.getId()).get();
        if (!updatedOrder.getOrderStatus().equals(OrderStatus.APPROVED_PENDING_SHIPMENT)
                && !order.getOrderStatus().equals(OrderStatus.CANCELLED)
                && !order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new UserDefinedException(UserDefinedException.ORDER_STATUS_NOT_APPROVED);
        }
        BigDecimal subTotal = new BigDecimal(0);
        BigDecimal gst = new BigDecimal(0);
        BigDecimal orderTotal = new BigDecimal(0);
        if(order.getCartItemList().size() ==1 && order.getCartItemList().get(0).getQty() ==0)
            throw new UserDefinedException(UserDefinedException.MINIMUM_ITEMS_IN_ORDER);
        for (CartItem cartItem : order.getCartItemList()) {
            if (cartItem.getQty() == 0)
                cartItemService.delete(cartItemService.findById(cartItem.getId()));
            else {
                CartItem updatedItem = cartItemService.update(cartItem);
                subTotal = subTotal.add(updatedItem.getSubTotal());
                gst = gst.add(updatedItem.getProduct().getGst().multiply(new BigDecimal(cartItem.getQty())));
            }
        }
        updatedOrder.setSubTotal(subTotal);
        updatedOrder.setOrderStatus(order.getOrderStatus());
        updatedOrder.setGst(gst);
        updatedOrder.setOrderTotal(subTotal.add(gst));
        save(updatedOrder);
      return updatedOrder;
    }
}