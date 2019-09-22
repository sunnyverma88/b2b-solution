package com.techieonthenet.dto;

import com.techieonthenet.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ShoppingCartDto {

    private List<CartItem> cartItems = new ArrayList<>();

    public ShoppingCartDto() {
        //default constructor
    }
}