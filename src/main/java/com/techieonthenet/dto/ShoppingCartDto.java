package com.techieonthenet.dto;

import com.techieonthenet.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Shopping cart dto.
 */
@Getter
@Setter
public class ShoppingCartDto {

    private List<CartItem> cartItems = new ArrayList<>();
    private BigDecimal gst;
    private BigDecimal grandTotal;
    private BigDecimal cartTotal;
    private int totalItems;

    /**
     * Instantiates a new Shopping cart dto.
     */
    public ShoppingCartDto() {
        //default constructor
    }
}