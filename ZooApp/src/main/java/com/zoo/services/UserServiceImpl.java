package com.zoo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.models.Animals;
import com.zoo.models.User;
import com.zoo.repositories.AnimalsRepository;
import com.zoo.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository urepo;

	@Autowired
	private AnimalsRepository arepo;
	
	@Override
	public boolean createAccount(User user) {
		log.info("In service layer, trying to create new user: " + user);
		int pk = urepo.save(user).getUserId();
		return (pk > 0) ? true : false;
	}
	
	@Override
	public boolean editUser(User user) {
		log.info("In service layer, trying to update user id#" + user.getUserId() + " to: " + user);
		//return urepo.update(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getUserId());
		User target = urepo.findById(user.getUserId());
		target.setFirstName(user.getFirstName());
		target.setLastName(user.getLastName());
		target.setEmail(user.getEmail());
		target.setPassword(user.getPassword());
		return (urepo.save(target) != null) ? true : false;
	}

	@Override
	public boolean deleteUser(User user) {
		log.info("In service layer, trying to delete user: " + user);
		urepo.delete(user);
		return true;
	}

	@Override
	public User findUsernameById(int id) {
		log.info("In service layer, trying to find username by id#: " + id);
		System.out.println("ID: " + id);
	return urepo.findById(id);
	}

	@Override
	public User login(String username, String password) {
		log.info("In service layer, trying to login user: " + username + password);
		return urepo.userLogin(username, password);
	}
	@Override
	public Animals findAnimalById(int id) {
		log.info("In service layer, trying to find animal by id: " + id);
		System.out.println("Animal Name: " + id);
		return urepo.findAnimals(id);
	}

}
