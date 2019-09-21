package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products")
@Getter
@Setter
@SequenceGenerator(name = "product_generator", sequenceName = "product_seq", allocationSize = 1)
public class Product extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    private Long id;

    private String name;
    private String brand;
    private String description;
    private String specifications;
    private BigDecimal actualPrice;
    private BigDecimal mrpPrice;
    private int shippingDays;
    private int hsnCode;


    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private boolean isVisible;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> imgList;

}
