package com.techieonthenet.dto;

import com.techieonthenet.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 890345L;
    private String name;
    private String description;
    private boolean isVisible;
    private List<Product> products;

}