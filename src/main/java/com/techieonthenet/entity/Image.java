package com.techieonthenet.entity;

import com.techieonthenet.entity.common.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Image.
 */
@Entity
@Getter
@Setter
public class Image extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_generator")
    @SequenceGenerator(name = "image_generator", sequenceName = "image_seq", allocationSize = 1)
    private Long id;
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    /**
     * Instantiates a new Image.
     *
     * @param imgUrl the img url
     */
    public Image(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Instantiates a new Image.
     */
    public Image() {
    }
}
