package com.zoo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zoo.models.User;
import com.zoo.repositories.UserRepository;

public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository urepo;

	@Override
	public boolean createAccount(User user) {
		int pk = urepo.save(user).getUserId();
		return (pk > 0) ? true : false;
	}
	
	@Override
	public boolean editUser(User user) {
		return urepo.update(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserId());
	}

	@Override
	public boolean deleteUser(User user) {
		urepo.delete(user);
		return true;
	}

	@Override
	public User findUsernameById(int id) {
		System.out.println("ID: " + id);
	return urepo.findById(id);
	}

	@Override
	public User login(String username, String password) {
		return urepo.userLogin(username, password);
	}


}
