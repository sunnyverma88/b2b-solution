package com.techieonthenet.service;

import com.techieonthenet.entity.User;
import com.techieonthenet.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class InitDBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDBService.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostConstruct
    public void createUser() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("jadams");
        user1.setPassword("p");
        user1.setEmail("JAdams@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user1, roleService.findByName("USER")));
        userService.createUser(user1, userRoles);

        userRoles.clear();

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword("p");
        user2.setEmail("Admin@gmail.com");
        userRoles.add(new UserRole(user1, roleService.findByName("ADMIN")));
        userService.createUser(user2, userRoles);

        userRoles.clear();

        User user3 = new User();
        user3.setFirstName("Super");
        user3.setLastName("Admin");
        user3.setUsername("superadmin");
        user3.setPassword("p");
        user3.setEmail("SuperAdmin@gmail.com");
        userRoles.add(new UserRole(user3, roleService.findByName("SUPER_ADMIN")));
        userService.createUser(user3, userRoles);
    }
}
