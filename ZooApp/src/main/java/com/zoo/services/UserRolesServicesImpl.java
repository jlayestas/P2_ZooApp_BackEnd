package com.zoo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.repositories.UserRoleRepository;
import com.zoo.models.UserRole;

@Service
@Transactional
public class UserRolesServicesImpl implements UserRoleServices {

	@Autowired
	private UserRoleRepository rRepo;
	@Override
	public boolean createRole(UserRole userRole) {
		int pk = rRepo.save(userRole).getId();
		return (pk > 0) ? true : false;
	}

	@Override
	public UserRole getRoleById(int id) {
		return rRepo.findById(id);
	}

	@Override
	public List<UserRole> getAllRoles() {
		return rRepo.findAll();
	}

	@Override
	public boolean updateRole(UserRole userRole) {
		return rRepo.update(userRole.getRole(), userRole.getId());
	}

	@Override
	public boolean deleteUserRole(UserRole userRole) {
		return rRepo.delete(userRole.getId());
	}

}
