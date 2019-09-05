package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category extends Auditable implements Serializable {

    private static final long serialVersionUID = 890345L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private boolean isVisible;
}