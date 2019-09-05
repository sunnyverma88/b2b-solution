package com.techieonthenet;

import com.techieonthenet.config.security.SecurityUtility;
import com.techieonthenet.entity.Role;
import com.techieonthenet.entity.User;
import com.techieonthenet.entity.UserRole;
import com.techieonthenet.service.UserService;
import com.techieonthenet.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BootstrapApp{




	public static void main(String[] args) {
		SpringApplication.run(BootstrapApp.class, args);
	}

}
