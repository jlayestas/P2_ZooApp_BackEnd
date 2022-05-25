package com.zoo.test.Services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.zoo.models.UserRole;
import com.zoo.repositories.UserRoleRepository;
import com.zoo.services.UserRoleServices;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleTests {

	
	@Mock
	private UserRoleRepository userRepo;
	@Mock
	private UserRoleServices service;
	
	private UserRole user = new UserRole(2, "user");
	private UserRole manager = new UserRole(1, "manager");
	private UserRole notARole = new UserRole(3, "Bad Data");
	
	List<UserRole> mockDB = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		mockDB.add(manager);
		mockDB.add(user);
		MockitoAnnotations.initMocks(this);
		Mockito.when(service.createRole(user)).thenReturn(true);
		Mockito.when(service.getRoleById(user.getId())).thenReturn(user);
		Mockito.when(service.getRoleById(notARole.getId())).thenReturn(null);
		Mockito.when(service.getAllRoles()).thenReturn(mockDB);
		Mockito.when(service.updateRole(new UserRole(user.getId(), "u"))).thenReturn(true);
		Mockito.when(service.updateRole(new UserRole(notARole.getId(), "u"))).thenReturn(false);
		Mockito.when(service.deleteUserRole(user)).thenReturn(true);
		Mockito.when(service.deleteUserRole(notARole)).thenReturn(false);
	}
	
	@Test
	public void createUserRoleTest() {
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
		assertEquals(true, service.updateRole(new UserRole(user.getId(), "u")));
	}
	@Test
	public void updateBadData() {
		assertEquals(false, service.updateRole(new UserRole(notARole.getId(), "u")));
	}
	@Test
	public void deleteUser() {
		assertEquals(true, service.deleteUserRole(user));
	}
	@Test
	public void deleteBadData() {
		assertEquals(false, service.deleteUserRole(notARole));
	}
}
