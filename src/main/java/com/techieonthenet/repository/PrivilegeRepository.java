package com.techieonthenet.repository;

import com.techieonthenet.entity.Privilege;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The interface Privilege repository.
 */
public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, Long> {

    /**
     * Find by name privilege.
     *
     * @param name the name
     * @return the privilege
     */
    Privilege findByName(String name);
}
