package com.techieonthenet.repository;

import com.techieonthenet.entity.Group;
import com.techieonthenet.entity.Order;
import com.techieonthenet.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Order repository.
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {


    /**
     * Find by user page.
     *
     * @param user     the user
     * @param pageable the pageable
     * @return the page
     */
    Page<Order> findByUser(User user, Pageable pageable);

    /**
     * Find by group page.
     *
     * @param group    the group
     * @param pageable the pageable
     * @return the page
     */
    Page<Order> findByGroup(Group group, Pageable pageable);
}
