package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Role;
import com.techieonthenet.repository.RoleRepository;
import com.techieonthenet.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepo;

    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return null;
    }
}
