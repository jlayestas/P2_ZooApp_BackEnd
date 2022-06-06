package com.zoo.test.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoo.controller.UserRoleController;
import com.zoo.models.UserRole;
import com.zoo.repositories.UserRoleRepository;
import com.zoo.services.UserRoleServices;
import com.zoo.services.UserRolesServicesImpl;
import com.zoo.util.ClientMessageUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRoleController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRoleControllerTest {
	
	private static UserRole mockURole1;
	private static UserRole mockURole2;
	private static UserRole mockURoleCreation;
	private static UserRole mockURoleModification;
	private static UserRole mockURoleDeletion;
	private static List<UserRole> dummyDb;
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	UserRoleController uRoleController;
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private UserRoleServices urserv;
	
	@SuppressWarnings("deprecation")
	public boolean isValidJSON (final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getJsonFactory().createJsonParser(json);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException jpe) {
			jpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return valid;
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() :: building test objects...");
		//id, role
		mockURole1 = new UserRole(1, "manager");
		mockURole2 = new UserRole(2, "user");
		
		mockURoleCreation = new UserRole("guest");
		
		mockURoleModification = mockURoleCreation;
		mockURoleModification.setRole("guest");
		
		mockURoleDeletion = new UserRole(4, "admin");
		
		dummyDb =  new ArrayList<UserRole>();
		dummyDb.add(mockURole1);
		dummyDb.add(mockURole2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(uRoleController).isNotNull();
	}
	
//	@Test
//	@Order(2)
//	@DisplayName("2. Create User role - Happy Path Scenerio Test")
//	public void testCreateUserRole() throws Exception {
//		// id number of this creation should be 3
//		mockURoleCreation.setId(3);
//		//tell Mockito the behavior that I want this method to act like in the mock environment
//		when(urserv.createRole(mockURoleCreation)).thenReturn(true);;
//		
//		//act
//		RequestBuilder request = MockMvcRequestBuilders.post("/api/userRole/createRole")
//				.accept(MediaType.APPLICATION_JSON_VALUE)
//				.content(om.writeValueAsString(mockURoleCreation))
//				.contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mockmvc.perform(request).andReturn();
//		//assert
//		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
//				result.getResponse().getContentAsString());
//	}
	
//	@Test
//	@Order(3)
//	@DisplayName("3. Get User Role by ID - Happy Path Scenerio Test")
//	public void testGetRoleById() throws Exception {
//		when(urserv.getRoleById(1)).thenReturn(mockURole1);
//		RequestBuilder request = MockMvcRequestBuilders.get("/api/userRole/GetRole?id=1");
//		MvcResult result = mockmvc.perform(request).andReturn();
//		assertEquals(om.writeValueAsString(mockURole1), result.getResponse().getContentAsString());
//	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get All User Roles - Happy Path Scenerio Test")
	public void testGetAllRoles() throws Exception {
		when(urserv.getAllRoles()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/userRole/GetRoles");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(dummyDb), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update an Existing Role - Happy Path Scenerio Test")
	// @Disabled("Disabled until CreateCandyTest is up!")
	public void testUpdateRole() throws Exception {
		when(urserv.updateRole(mockURoleModification)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/userRole/UpdateRole")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockURoleModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete Role - Happy Path Scenerio Test")
	public void testDeleteRole() throws Exception {
		when(urserv.deleteUserRole(mockURoleDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/userRole/DeleteRole")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockURoleDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(7)
	@DisplayName("6. Unneccessay/Unused Test")
	@Disabled("Disabled until CreateCandyTest is up!") 
	// @Disabled will allow you to ignore this test while debugging other tests
	public void unusedTest() {
		return;
	}
}
