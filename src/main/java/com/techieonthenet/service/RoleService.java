package com.techieonthenet.service;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;

import java.util.Set;

/**
 * The interface Role service.
 */
public interface RoleService {

    /**
     * Create role role.
     *
     * @param role       the role
     * @param privileges the privileges
     * @return the role
     */
    Role createRole(Role role , Set<Privilege> privileges);

    /**
     * Find by name role.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);

    /**
     * Save.
     *
     * @param role the role
     */
    void save(Role role);

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<Role> findAll();
}
