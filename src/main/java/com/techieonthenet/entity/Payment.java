package com.techieonthenet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;


/**
 * The type Payment.
 */
public class Payment implements Serializable {
    private static final long serialVersionUID = 79151235145L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;


    @OneToOne
    @JsonIgnore
    private Order order;


}