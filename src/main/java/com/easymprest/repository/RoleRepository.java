package com.easymprest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.easymprest.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(String role);
	
	List<Role>findAll();

}
