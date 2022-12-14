package com.example.springbootjwttoken.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootjwttoken.model.AppRole;
import com.example.springbootjwttoken.model.AppUser;
import com.example.springbootjwttoken.repo.RoleRepo;
import com.example.springbootjwttoken.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor 
@Transactional 
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
	
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder; 
	
	@Override
	public AppUser saveUser(AppUser appUser) {
		log.info("Saving new user {} to the DB", appUser.getName());
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return userRepo.save(appUser);
	}

	@Override
	public AppRole saveRole(AppRole appRole) {
		log.info("Saving new role {} to the DB", appRole.getName());
		return roleRepo.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		
		AppUser appUser = userRepo.findByUsername(username);
		AppRole appRole = roleRepo.findByName(roleName);
		
		log.info("Adding role {} to user {} on the DB", appUser.getName(), appRole.getName());
		
		appUser.getRoles().add(appRole);	
	}

	@Override
	public AppUser getUser(String username) {
		log.info("Fetching user {} from the DB", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser appUser = userRepo.findByUsername(username);
		
		if(appUser == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in the dabase: {}", username);
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		appUser.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return new User(appUser.getUsername(), appUser.getPassword(), authorities);
	}
}
