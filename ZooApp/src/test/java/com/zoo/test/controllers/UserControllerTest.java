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
import com.zoo.controller.UserController;
import com.zoo.models.User;
import com.zoo.models.UserRole;
import com.zoo.repositories.UserRepository;
import com.zoo.services.UserService;
import com.zoo.services.UserServiceImpl;
import com.zoo.util.ClientMessageUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
	
	private static User mockUser1;
	private static User mockUser2;
	private static User mockUserCreation;
	private static User mockUserModification;
	private static User mockUserDeletion;
	private static List<User> dummyDb;
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	UserController userController;
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private UserService uservice;
	
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
		//user id, username, password, first name, last name, email, user role
		mockUser1 = new User(1, "CRock", "passWord", "Chris", "Rock", "Crock@gmail.com", new UserRole(1, "manager"));
		mockUser1 = new User(2, "AHeard", "PooOnBed", "Amber", "Heard", "AHeard@gmail.com", new UserRole(2, "visitor"));
		
		mockUserCreation = new User("WSmith", "password", "Will", "Smith", "WSmith@gmail.com", new UserRole(3, "manager"));
		
		mockUserModification = mockUserCreation;
		mockUserModification.setUsername("WSmith");
		mockUserModification.setPassword("password");
		mockUserModification.setFirstName("Will");
		mockUserModification.setLastName("Smith");
		mockUserModification.setEmail("WSmith@gmailc.om");
	

		mockUserDeletion = new User(4 ,"Fluffy", "Password", "Gabriel", "Iglesias", "Fulffy@gmail.com", new UserRole(4, "visitor"));
		
		dummyDb = new ArrayList<User>();;
		dummyDb.add(mockUser1);
		dummyDb.add(mockUser2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Create User - Happy Path Scenerio Test")
	public void testCreateUser() throws Exception {
		// id number of this creation should be 3
		mockUserCreation.setUserId(3);
		//tell Mockito the behavior that I want this method to act like in the mock environment
		when(uservice.createAccount(mockUserCreation)).thenReturn(true);
		
		//act
		RequestBuilder request = MockMvcRequestBuilders.post("/api/users/createUser")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		//assert
		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}

	@Test
	@Order(3)
	@DisplayName("3. Get User by ID - Happy Path Scenerio Test")
	public void testGetById() throws Exception {
		when(uservice.findUsernameById(1)).thenReturn(mockUser1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/users/user?id=1");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockUser1), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Update an Existing user - Happy Path Scenerio Test")
	// @Disabled("Disabled until CreateCandyTest is up!")
	public void testUpdateUser() throws Exception {
		when(uservice.editUser(mockUserModification)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/users/updateUser")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	
	@Test
	@Order(5)
	@DisplayName("5. Delete User - Happy Path Scenerio Test")
	public void testDeleteCandy() throws Exception {
		when(uservice.deleteUser(mockUserDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/users/deleteUser")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Unneccessay/Unused Test")
	@Disabled("Disabled until CreateCandyTest is up!") 
	// @Disabled will allow you to ignore this test while debugging other tests
	public void unusedTest() {
		return;
	}
}
