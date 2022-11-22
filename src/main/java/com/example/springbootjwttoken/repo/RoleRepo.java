package com.example.springbootjwttoken.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootjwttoken.model.AppRole;


public interface RoleRepo extends JpaRepository<AppRole, Long>{

	AppRole findByName(String name);
}
