package com.zoo.services;

import com.zoo.models.Animals;
import com.zoo.models.User;

public interface UserService {
	//create
	public boolean createAccount(User user);
	
	//find username by id number
	public User findUsernameById(int id);
	
	//find animal by id 
	public Animals findAnimalById(int id);
	
	//update info
	public boolean editUser(User user);
	
	//delete
	public boolean deleteUser(User user);
	
	//login
	public User login (String username, String password);
}
