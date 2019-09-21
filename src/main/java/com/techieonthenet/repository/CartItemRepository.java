package com.techieonthenet.repository;

import com.techieonthenet.entity.CartItem;
import com.techieonthenet.entity.ShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends PagingAndSortingRepository<CartItem, Long> {

    List<CartItem> findByShoppingCart(ShoppingCart cart);

}
