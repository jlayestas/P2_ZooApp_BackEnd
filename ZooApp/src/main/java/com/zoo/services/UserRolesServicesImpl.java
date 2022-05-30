package com.zoo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.repositories.UserRoleRepository;
import com.zoo.models.UserRole;

@Service
@Transactional
public class UserRolesServicesImpl implements UserRoleServices {

	private static Logger log = Logger.getLogger(UserRolesServicesImpl.class);
	
	@Autowired
	private UserRoleRepository rRepo;
	

	@Override
	public boolean createRole(UserRole userRole) {
		log.info("In service layer, trying to create new user role: " + userRole);
		int pk = rRepo.save(userRole).getId();
		return (pk > 0) ? true : false;
	}

	@Override
	public UserRole getRoleById(int id) {
		log.info("In service layer, trying to find role by id: " + id);
		return rRepo.findById(id);
	}

	@Override
	public List<UserRole> getAllRoles() {
		log.info("In service layer, trying to find all roles");
		return rRepo.findAll();
	}

	@Override
	public boolean updateRole(UserRole userRole) {
		log.info("In service layer, trying to update user role id# " + userRole.getId() + " to: " + userRole);
		if(rRepo.update(userRole.getRole(), userRole.getId()).equals(userRole)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteUserRole(UserRole userRole) {
		log.info("In service layer, trying to delete user role: " + userRole);
		rRepo.delete(userRole);
		return true;
	}

}
