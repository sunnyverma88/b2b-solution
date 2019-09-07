package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;
import com.techieonthenet.repository.PrivilegeRepository;
import com.techieonthenet.service.PrivilegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

@Autowired
    PrivilegeRepository privilegeRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public void save(Privilege priv) {
        privilegeRepository.save(priv);
    }

    @Override
    public Privilege createPriv (Privilege priv) {
        Privilege localPriv = privilegeRepository.findByName(priv.getName());
        LOGGER.info("Creating privileges");
        if (localPriv != null) {
            LOGGER.info("Privilege with name {} already exist. Nothing will be done. ", priv.getName());
        } else {
            localPriv= privilegeRepository.save(priv);
        }

        return localPriv;
    }

    @Override
    public Privilege findByName(String name)
    {
        return privilegeRepository.findByName(name);
    }
}
