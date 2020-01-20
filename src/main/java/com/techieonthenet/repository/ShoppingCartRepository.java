package com.techieonthenet.repository;

import com.techieonthenet.entity.ShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {

    ShoppingCart findByUserId(Long userId);


}
