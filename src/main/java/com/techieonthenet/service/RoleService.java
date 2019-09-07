package com.techieonthenet.service;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;

import java.util.Set;

public interface RoleService {

    Role createRole(Role role , Set<Privilege> privileges);

    Role findByName(String name);

    void save(Role role);
}
