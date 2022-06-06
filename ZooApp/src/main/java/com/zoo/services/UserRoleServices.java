package com.zoo.services;

import java.util.List;

import com.zoo.models.UserRole;


public interface UserRoleServices {

	boolean createRole(UserRole userRole);

	UserRole getRoleById(int id);
	
	List<UserRole> getAllRoles();
	
	boolean updateRole(UserRole userRole);
	boolean deleteUserRole(UserRole userRole);
}
