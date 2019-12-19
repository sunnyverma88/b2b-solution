package com.techieonthenet.entity;

import com.techieonthenet.config.LocalDateConverter;
import com.techieonthenet.entity.common.Auditable;
import com.techieonthenet.entity.common.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order.
 */
@Entity
@Getter
@Setter
@Table(name="Orders")
@SequenceGenerator(name = "order_generator", sequenceName = "order_seq", allocationSize = 1)
public class Order extends Auditable implements Serializable {

    private static final long serialVersionUID = 2893475845L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    private Long id;

    @Convert(converter = LocalDateConverter.class)
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private BigDecimal gst;

    private BigDecimal subTotal;

    private BigDecimal orderTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItemList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<TaskItem> taskItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}