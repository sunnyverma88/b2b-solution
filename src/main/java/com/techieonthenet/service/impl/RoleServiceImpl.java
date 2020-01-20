package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;
import com.techieonthenet.repository.RoleRepository;
import com.techieonthenet.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepo;


    private static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public Role findByName(String name) {
        Role role = roleRepo.findByName(name);
        return roleRepo.findByName(name);
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role createRole(Role role , Set<Privilege> privileges) {
        Role localRole = roleRepo.findByName(role.getName());
        LOGGER.info("Creating Role");
        if (localRole != null) {
            LOGGER.info("Role with name {} already exist. Nothing will be done. ", role.getName());
        } else {
            role.getPrivileges().addAll(privileges);
            localRole=roleRepo.save(role);
        }

        return localRole;
    }


}
