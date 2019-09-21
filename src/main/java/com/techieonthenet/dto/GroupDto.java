package com.techieonthenet.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GroupDto {

    private String name;
    private String website;

    private String type;
    private String gstNo;
    private BigDecimal profitPercentage;
    private BigDecimal approvalThreshold;

    private String addressline1;
    private String addressline2;
    private String landmark;
    private int zipcode;
    private String city;
    private String state;

    private String adminFirstName;
    private String adminLastName;

    private String adminEmail;

}
