package com.techieonthenet.service;

import com.techieonthenet.entity.Product;

/**
 * The interface Product service.
 */
public interface ProductService {

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<Product> findAll();

    /**
     * Save product.
     *
     * @param product the product
     * @return the product
     */
    Product save(Product product);

    /**
     * Find by id product.
     *
     * @param productId the product id
     * @return the product
     */
    Product findById(Long productId);

}
