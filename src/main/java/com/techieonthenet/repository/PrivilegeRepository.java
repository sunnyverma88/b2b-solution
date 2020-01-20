package com.techieonthenet.repository;

import com.techieonthenet.entity.Privilege;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, Long> {

    Privilege findByName(String name);
}
