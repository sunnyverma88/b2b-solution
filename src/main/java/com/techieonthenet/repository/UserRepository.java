package com.techieonthenet.repository;

import com.techieonthenet.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    User findByUsernameAndEnabled(String username, boolean b);
}