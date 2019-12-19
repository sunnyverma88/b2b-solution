package com.techieonthenet.repository;

import com.techieonthenet.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * The interface User repository.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail(String email);

    List<User> findAll();

    /**
     * Find by username and enabled user.
     *
     * @param username the username
     * @param b        the b
     * @return the user
     */
    User findByUsernameAndEnabled(String username, boolean b);
}