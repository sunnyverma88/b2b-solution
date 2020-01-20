package com.techieonthenet.repository;

import com.techieonthenet.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    /**
     * Find by name role.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);

}
