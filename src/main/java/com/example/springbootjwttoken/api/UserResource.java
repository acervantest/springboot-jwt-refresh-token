package com.example.springbootjwttoken.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootjwttoken.model.AppUser;
import com.example.springbootjwttoken.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api") 
@RequiredArgsConstructor
public class UserResource {
	
	private final UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<AppUser>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}

}
