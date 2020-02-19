package com.easymprest.repository;

import org.springframework.data.repository.CrudRepository;

import com.easymprest.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);

}
