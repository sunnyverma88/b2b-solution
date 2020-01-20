package com.techieonthenet.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private String brand;
    private String description;
    private String specifications;
    private BigDecimal sellingPrice;
    private BigDecimal mrpPrice;
    private String imgUrl;
    private Long categoryId;
    private int shippingDays;
    private int hsnCode;
    private boolean isVisible;
}
