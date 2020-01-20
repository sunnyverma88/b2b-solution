package com.techieonthenet.repository;

import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.ShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Cart item repository.
 */
@Repository
public interface CartItemRepository extends PagingAndSortingRepository<CartItem, Long> {

    /**
     * Find by shopping cart list.
     *
     * @param cart the cart
     * @return the list
     */
    List<CartItem> findByShoppingCart(ShoppingCart cart);

}
