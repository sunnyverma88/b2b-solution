package com.techieonthenet.repository;

import com.techieonthenet.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {


}
