package com.techieonthenet.repository;

import com.techieonthenet.entity.Group;
import com.techieonthenet.entity.Order;
import com.techieonthenet.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {


    Page<Order> findByUser(User user, Pageable pageable);

    Page<Order> findByGroup(Group group, Pageable pageable);
}
