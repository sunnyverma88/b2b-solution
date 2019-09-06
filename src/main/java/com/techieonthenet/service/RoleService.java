package com.techieonthenet.service;

import com.techieonthenet.entity.Role;

public interface RoleService {

    Role createRole(Role role);

    Role findByName(String name);

    Role save(Role role);
}
