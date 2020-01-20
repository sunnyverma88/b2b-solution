package com.techieonthenet.entity;

import com.techieonthenet.config.LocalDateConverter;
import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The type Cart item.
 */
@Entity
@Getter
@Setter
@SequenceGenerator(name = "cartitem_generator", sequenceName = "cart_item_seq", allocationSize = 1)
public class CartItem extends Auditable implements Serializable {

    private static final long serialVersionUID = -189412481L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartitem_generator")
    private Long id;
    private int qty;
    private BigDecimal subTotal;
    private BigDecimal grandTotal;

    @Convert(converter = LocalDateConverter.class)
    private LocalDate deliveryDate;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}