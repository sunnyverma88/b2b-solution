package com.techieonthenet.entity;

import com.techieonthenet.entity.common.AddressType;
import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "address_generator", sequenceName = "addr_seq", allocationSize = 1)
public class Address extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    private String addressline1;
    private String addressline2;
    private String landmark;
    private int zipcode;
    private String city;
    private String state;
}
