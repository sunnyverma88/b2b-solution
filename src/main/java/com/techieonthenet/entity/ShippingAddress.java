package com.techieonthenet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@Setter
public class ShippingAddress extends Address implements Serializable {

    private static final long serialVersionUID = 189013457L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shippingAddressName;
    private String shippingAddressStreet1;
    private String shippingAddressStreet2;
    private String shippingAddressCity;
    private String shippingAddressState;
    private String shippingAddressZipcode;

    @OneToOne
    @JsonIgnore
    private Order order;


}