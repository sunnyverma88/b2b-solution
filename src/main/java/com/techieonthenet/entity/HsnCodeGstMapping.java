package com.techieonthenet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "HSNCODE_GST_MAPPING")
@Getter
@Setter
public class HsnCodeGstMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hsn_generator")
    @SequenceGenerator(name = "hsn_generator", sequenceName = "hsn_seq", allocationSize = 1)
    private Long id;
    private int hsnCode;
    private int gstPercentage;


}
