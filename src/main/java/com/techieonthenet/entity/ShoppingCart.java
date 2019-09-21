package com.techieonthenet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shopping_cart")
@SequenceGenerator(name = "shopping_cart_generator", sequenceName = "shopping_cart_seq", allocationSize = 1)
public class ShoppingCart extends Auditable implements Serializable {

    private static final long serialVersionUID = -891273432L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_cart_generator")
    private Long id;
    private BigDecimal gst;
    private BigDecimal grandTotal;
    private BigDecimal cartTotal;
    private int totalItems;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CartItem> cartItemList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}