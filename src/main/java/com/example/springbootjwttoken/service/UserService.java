package com.example.springbootjwttoken.service;

import java.util.List;

import com.example.springbootjwttoken.model.AppRole;
import com.example.springbootjwttoken.model.AppUser;


public interface UserService {
	
	AppUser saveUser(AppUser appUser);
	
	AppRole saveRole(AppRole appRole);
	
	void addRoleToUser(String username, String roleName);
	
	AppUser getUser(String username);
	
	List<AppUser> getUsers();

}
