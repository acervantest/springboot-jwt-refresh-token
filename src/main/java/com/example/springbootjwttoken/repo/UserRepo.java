package com.example.springbootjwttoken.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootjwttoken.model.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long> {
	
	AppUser findByUsername(String username);
	
}