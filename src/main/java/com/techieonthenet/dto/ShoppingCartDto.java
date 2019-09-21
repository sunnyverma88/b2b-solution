package com.techieonthenet.dto;

import com.techieonthenet.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ShoppingCartDto {

    private List<CartItem> cartItems;

    public ShoppingCartDto() {
        //default constructor
    }
}