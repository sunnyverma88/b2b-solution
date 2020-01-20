package com.techieonthenet.service;

import com.techieonthenet.entity.Product;

public interface ProductService {

    Iterable<Product> findAll();

    Product save(Product product);

    Product findById(Long productId);

}
