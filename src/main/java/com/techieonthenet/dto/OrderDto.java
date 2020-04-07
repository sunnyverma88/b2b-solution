package com.techieonthenet.dto;

import com.techieonthenet.entity.*;
import com.techieonthenet.entity.common.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto {


    private Long id;

    private LocalDate orderDate;

    private OrderStatus orderStatus;

    private BigDecimal gst;

    private BigDecimal subTotal;

    private BigDecimal orderTotal;

    private List<CartItem> cartItemList = new ArrayList<>();

    private Address address;

    private List<TaskItem> taskItems = new ArrayList<>();

    private User user;

    private Group group;

    private String comment;

}