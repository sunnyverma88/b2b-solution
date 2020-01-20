package com.techieonthenet.repository;

import com.techieonthenet.entity.ShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Shopping cart repository.
 */
@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {

    /**
     * Find by user id shopping cart.
     *
     * @param userId the user id
     * @return the shopping cart
     */
    ShoppingCart findByUserId(Long userId);


}
