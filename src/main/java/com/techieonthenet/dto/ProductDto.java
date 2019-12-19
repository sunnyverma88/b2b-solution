package com.techieonthenet.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * The type Product dto.
 */
@Getter
@Setter
public class ProductDto {

    private String name;
    private String brand;
    private String description;
    private String specifications;
    private BigDecimal actualPrice;
    private BigDecimal mrpPrice;
    private String imgUrl;
    private Long categoryId;
    private int shippingDays;
    private int hsnCode;
    private boolean isVisible;
}
