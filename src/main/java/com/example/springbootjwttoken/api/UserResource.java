package com.example.springbootjwttoken.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springbootjwttoken.model.AppRole;
import com.example.springbootjwttoken.model.AppUser;
import com.example.springbootjwttoken.service.UserService;

import lombok.Data;
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
	
	@PostMapping("/user/save")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveUser(user));
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}
	
	@PostMapping("/role/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
		userService.addRoleToUser(roleToUserForm.getUserName(), roleToUserForm.getRoleName());
		return ResponseEntity.ok().build();
	}
}
	
@Data
class RoleToUserForm {
	private String userName;
	private String roleName;
}

