package com.example.springbootjwttoken;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springbootjwttoken.model.AppRole;
import com.example.springbootjwttoken.model.AppUser;
import com.example.springbootjwttoken.service.UserService;

@SpringBootApplication
public class SpringbootJwtTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtTokenApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
			userService.saveRole(new AppRole(null, "ROLE_USER"));
			userService.saveRole(new AppRole(null, "ROLE_MANAGER"));
			userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
			userService.saveRole(new AppRole(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new AppUser(null ,"John Travolta", "john", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null ,"Will Smith", "will", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null ,"Jim Carrey", "jim", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null ,"Arnold Schwarzenneger", "arnold", "1234", new ArrayList<>()));
			
			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");
			
			
			
		};
	}

}
