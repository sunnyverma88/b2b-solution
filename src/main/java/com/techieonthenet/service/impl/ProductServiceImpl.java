package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Product;
import com.techieonthenet.repository.ProductRepository;
import com.techieonthenet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Product service.
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * The Product repository.
     */
    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).get();
    }


}
