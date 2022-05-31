package com.zoo.test.Services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.zoo.models.UserRole;
import com.zoo.repositories.UserRoleRepository;
import com.zoo.services.UserRoleServices;
import com.zoo.services.UserRolesServicesImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleTests {

/*
 * methods to test:
 * @Override
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
 * */
	
	@Mock
	private UserRoleRepository userRepo;
	@InjectMocks
	private UserRolesServicesImpl service;
	
	private UserRole user = new UserRole(2, "user");
	private UserRole updatedUser = new UserRole(2, "u");
	private UserRole updatedManager = new UserRole(1, "m");
	private UserRole manager = new UserRole(1, "manager");
	private UserRole notARole = new UserRole(3, "Bad Data");
	
	List<UserRole> mockDB = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		mockDB.add(manager);
		mockDB.add(user);
		
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(userRepo.findById(user.getId())).thenReturn(user);
		Mockito.when(userRepo.findById(notARole.getId())).thenReturn(null);
		Mockito.when(userRepo.findAll()).thenReturn(mockDB);
		Mockito.when(userRepo.save(user)).thenReturn(user);
		Mockito.when(userRepo.update(updatedUser.getRole(), updatedUser.getId())).thenReturn(true);
		Mockito.when(userRepo.update(updatedManager.getRole(), updatedManager.getId())).thenReturn(true);
		Mockito.when(userRepo.update(notARole.getRole(), notARole.getId())).thenReturn(false);
		Mockito.when(userRepo.delete(user.getId())).thenReturn(true);
		Mockito.when(userRepo.delete(manager.getId())).thenReturn(true);
		Mockito.when(userRepo.delete(notARole.getId())).thenReturn(false);
	}
	
	@Test
	public void createUserRoleTest() {
//		assertEquals(user.getId(), service.createRole(user));
		assertTrue(service.createRole(user));
	}
	@Test
	public void getUser() {
		assertEquals(user, service.getRoleById(user.getId()));
	}

	@Test
	public void getBadData() {
		assertEquals(null, service.getRoleById(notARole.getId()));
	}
	@Test
	public void getAll() {
		assertEquals(mockDB, service.getAllRoles());
	}
	@Test
	public void updateUser() {
		assertEquals(true, service.updateRole(updatedUser));
	}
	@Test
	public void updateManager() {
		assertEquals(true, service.updateRole(updatedManager));
	}
	@Test
	public void updateBadData() {
		assertEquals(false, service.updateRole(notARole));
	}
	@Test
	public void deleteUser() {
		assertEquals(true, service.deleteUserRole(user));
	}
	@Test
	public void deleteManager() {
		assertEquals(true, service.deleteUserRole(manager));
	}
	@Test
	public void deleteBadData() {
		assertEquals(false, service.deleteUserRole(notARole));
	}
}
