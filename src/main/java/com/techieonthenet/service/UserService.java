package com.techieonthenet.service;

import java.util.Set;

import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.UserRole;


public interface UserService {

	public User createUser(User user, Set<Role> roles);

	User findByUsername(String username);
	
	User findByEmail (String email);
	
	User save(User user);
	
	User findById(Long id);
	
	
	
}
