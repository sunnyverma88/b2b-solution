package com.techieonthenet.service;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Component
public class InitDBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDBService.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PrivilegeService privilegeService;

    @PostConstruct
    public void createUser() {


        Set<Privilege> privileges = new HashSet<>();
        privileges.add(privilegeService.findByName("PRIV_VIEW_PRODUCT"));
        Role role1 = new Role();
        role1.setName("USER");
        roleService.createRole(role1, privileges);


        Role role2 = new Role();
        role2.setName("ADMIN");
        roleService.createRole(role2, privileges);


        Role role3 = new Role();
        role3.setName("SUPER_ADMIN");
        roleService.createRole(role3, privileges);


        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("jadams");
        user1.setPassword("p");
        user1.setEmail("JAdams@gmail.com");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findByName("USER"));
        userService.createUser(user1, userRoles);


        userRoles.clear();

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword("p");
        user2.setEmail("Admin@gmail.com");
        userRoles.add(roleService.findByName("ADMIN"));
        userService.createUser(user2, userRoles);

        userRoles.clear();

        User user3 = new User();
        user3.setFirstName("Super");
        user3.setLastName("Admin");
        user3.setUsername("superadmin");
        user3.setPassword("p");
        user3.setEmail("SuperAdmin@gmail.com");
        userRoles.add(roleService.findByName("SUPER_ADMIN"));
        userService.createUser(user3, userRoles);

        userRoles.clear();

    }
}
