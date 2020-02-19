package com.easymprest.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.easymprest.model.Role;
import com.easymprest.model.User;
import com.easymprest.repository.RoleRepository;
import com.easymprest.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



	@Override
	public User findUserByEmail(String email) {

		return userRepository.findByEmail(email);

	}

	@Override
	public void saveUser(User user) {
		
		User userExists = this.findUserByEmail(user.getEmail());
		
		if (userExists != null && userExists.getActive() == 0) {
			
			userExists.setName(user.getName());
			userExists.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userExists.setActive(1);
			
			userRepository.save(userExists);
			
		}else {
			
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

			user.setActive(1);

			Role role = new Role();
			role.setRole("USER");
			role.setId(2L);

			user.setRole("USER");
	        Role userRole = roleRepository.findByRole(user.getRole());

	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

			userRepository.save(user);
			
		}

	}
	
	@Override
	public void saveUserProvisorio(User user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		user.setActive(0);

        Role userRole = roleRepository.findByRole(user.getRole());

        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		userRepository.save(user);

	}

}
