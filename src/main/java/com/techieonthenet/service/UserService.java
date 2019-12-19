package com.techieonthenet.service;

import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;

import java.util.Set;


/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Create user user.
     *
     * @param user  the user
     * @param roles the roles
     * @return the user
     */
    User createUser(User user, Set<Role> roles);

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Find by username and enabled user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsernameAndEnabled(String username);

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail(String email);

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    User save(User user);

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findById(Long id);


}
