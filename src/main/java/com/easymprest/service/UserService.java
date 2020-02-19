package com.easymprest.service;

import com.easymprest.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);

	void saveUserProvisorio(User user);

}
