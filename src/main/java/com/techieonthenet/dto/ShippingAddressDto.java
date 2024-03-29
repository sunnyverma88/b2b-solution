package com.techieonthenet.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Shipping address dto.
 */
@Getter
@Setter
public class ShippingAddressDto {

    private String addressline1;
    private String addressline2;
    private String landmark;
    private int zipcode;
    private String city;
    private String state;


}
