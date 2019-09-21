package com.techieonthenet.repository;

import com.techieonthenet.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

   Role findByName(String name);

}
