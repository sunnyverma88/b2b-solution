package com.techieonthenet.repository;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    public Privilege findByName(String name);
}
