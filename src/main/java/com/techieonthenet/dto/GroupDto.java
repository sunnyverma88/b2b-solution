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

   // @Pattern(regexp = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}" , message = "GST is invalid")
    private String gstNo;

    private BigDecimal profitPercentage;
    private BigDecimal approvalThreshold;

    private String addressline1;
    private String addressline2;
    private String landmark;
    private int zipcode;
    private String city;
    private String state;


}
