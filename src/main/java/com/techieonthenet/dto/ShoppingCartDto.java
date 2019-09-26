package com.techieonthenet.dto;

import com.techieonthenet.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ShoppingCartDto {

    private List<CartItem> cartItems = new ArrayList<>();
    private BigDecimal gst;
    private BigDecimal grandTotal;
    private BigDecimal cartTotal;
    private int totalItems;

    public ShoppingCartDto() {
        //default constructor
    }
}