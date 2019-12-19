package com.techieonthenet.service;

import com.techieonthenet.entity.Privilege;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.ShoppingCart;
import com.techieonthenet.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


/**
 * The type Init db service.
 */
@Component
public class InitDBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDBService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Create user.
     */
    @PostConstruct
    public void createUser() {

        try {

            Set<Privilege> privileges = new HashSet<>();
            privileges.add(privilegeService.findByName("PRIV_VIEW_PRODUCT"));
            Role role1 = new Role();
            role1.setName("USER");
            roleService.createRole(role1, privileges);

            Role role4 = new Role();
            role4.setName("GROUP_ADMIN");
            roleService.createRole(role4, privileges);
            privileges.clear();


            privileges.add(privilegeService.findByName("PRIV_VIEW_PRODUCT"));
            privileges.add(privilegeService.findByName("PRIV_ADD_PRODUCT"));

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
            user1.setPasswordResetRequired(false);
            Set<Role> userRoles = new HashSet<>();
            LOGGER.info("User Role - {}", roleService.findByName("USER"));
            userRoles.add(roleService.findByName("USER"));
            userService.createUser(user1, userRoles);
            ShoppingCart cart1 = new ShoppingCart();


            userRoles.clear();

            User user2 = new User();
            user2.setFirstName("Admin");
            user2.setLastName("Admin");
            user2.setUsername("admin");
            user2.setPassword("p");
            user2.setPasswordResetRequired(false);
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
            user3.setPasswordResetRequired(false);
            userRoles.add(roleService.findByName("SUPER_ADMIN"));
            userService.createUser(user3, userRoles);
            userRoles.clear();

        } catch (Exception e) {
        }
    }

}
