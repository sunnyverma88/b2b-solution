package com.techieonthenet.service.impl;

import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
import com.techieonthenet.exception.UserAlreadyExist;
import com.techieonthenet.repository.RoleRepository;
import com.techieonthenet.repository.UserRepository;
import com.techieonthenet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user, Set<Role> roles) {
        user.setUsername(user.getEmail().toLowerCase());

        User localUser = findByUsernameAndEnabled(user.getUsername());
        if (localUser != null) {
            LOGGER.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
            throw new UserAlreadyExist("User with username - " + user.getUsername() +"already exist. Nothing will be done");
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            for (Role role : roles) {
                user.getRoles().add(role);
            }
            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username.toLowerCase());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsernameAndEnabled(String username) {
        System.out.println("Inside findByUsernameAndEnabled ");
        return userRepository.findByUsernameAndEnabled(username.toLowerCase(), true);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


}
