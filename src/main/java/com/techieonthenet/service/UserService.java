package com.techieonthenet.service;

import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;

import java.util.Set;


public interface UserService {

    User createUser(User user, Set<Role> roles);

    User findByUsername(String username);

    User findByUsernameAndEnabled(String username);

    User findByEmail(String email);

    User save(User user);

    User findById(Long id);


}
