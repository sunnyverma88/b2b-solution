package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The type Category.
 */
@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
public class Category extends Auditable implements Serializable {

    private static final long serialVersionUID = 890345L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private boolean isVisible;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

}