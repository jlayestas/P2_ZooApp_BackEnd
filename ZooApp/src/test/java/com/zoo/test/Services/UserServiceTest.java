package com.zoo.test.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zoo.models.Animals;
import com.zoo.models.HabitatType;
import com.zoo.models.User;
import com.zoo.models.UserRole;
import com.zoo.repositories.AnimalsRepository;
import com.zoo.repositories.UserRepository;
import com.zoo.services.AnimalsServiceImpl;
import com.zoo.services.UserServiceImpl;




@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	
	@Mock
	private static UserRepository udao;
	
	@InjectMocks
	private static UserServiceImpl userv;
	
	private static User u1, u2;
	static List<User> dummyDb;
	
	//For finding animal name
	@Mock
	private static AnimalsRepository arepo;
	
	@InjectMocks
	private static AnimalsServiceImpl aserv;
	
	private static Animals a1;
	static List<Animals> dummyDb1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//arrange your tests with needed dependencies
		//Mockito setup
		//1. mock the depending classes
		udao = Mockito.mock(UserRepository.class);
		
		//2. inject service with mocked classes
		userv = new UserServiceImpl(udao);
		
		//user id, username, password, first name, last name, email, user role
		u1 = new User(1, "CRock", "passWord", "Chris", "Rock", "CRock@gmail.com", new UserRole(1, "manager"));
		u2 = new User(2, "AHeard", "PooOnBed", "Amber", "Heard", "AHeard@gmail.com", new UserRole(2, "visitor"));
	
		
		dummyDb = new ArrayList<User>();;
		dummyDb.add(u1);
		dummyDb.add(u2);
		
		//for finding an animal name
		arepo = Mockito.mock(AnimalsRepository.class);
		
		aserv = new AnimalsServiceImpl(arepo);
		
		a1 = new Animals(1, "Lion", 50, "Carnivore", new HabitatType(1, "Africa"));
		dummyDb1 = new ArrayList<Animals>();
		dummyDb1.add(a1);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(udao).isNotNull();
		assertThat(userv).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Create User Happy Path Test")
	void testCreateUser() {
		//arrange step
		User u3 = new User("AHeard", "PooOnBed", "Amber", "Heard", "AHeard@gmail.com", new UserRole(2, "visitor"));
		u3.setUserId(3);
		
		//here we will tell mockito what type of behavior to expect from calling certain methods from our dao
		when(udao.save(u3)).thenReturn(u3);
		
		//act + assert step
		assertEquals(true, userv.createAccount(u3));

	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get Username by Id Happy Path Test")
	void testFindUserNameById() {
		//arrange step
		when(userv.findUsernameById(1)).thenReturn((u1));
		
		//act + assert step
		assertEquals(u1, userv.findUsernameById(1));
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Update User Happy Path Test")
	void testEditUser() {
		u2.setFirstName("Will");
		u2.setLastName("Smith");
		u2.setEmail("WSmith@gmail.com");
		u2.setPassword("PassWord");
		
		when(userv.findUsernameById(2)).thenReturn((u2));
		when(udao.save(u2)).thenReturn(u2);
		
		//act + assert step
		assertEquals(true, userv.editUser(u2));
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Delete User Happy Path Test")
	void testDeleteUser() {
		doNothing().when(udao).delete(u2);
		//act + assert step
		assertEquals(true, userv.deleteUser(u2));
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Login Happy Path Test")
	void testLogin() {
		when(udao.userLogin("CRock", "passWord")).thenReturn(u1);
		when(udao.userLogin("WSmith", "PassWord")).thenReturn(u2);
		
		assertEquals(u1, userv.login("CRock", "passWord"));
		assertEquals(u2, userv.login("WSmith", "PassWord"));
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Get Animal name by Id Happy Path Test")
	void testFindAnimalByName() {
		//arrange step
		when(userv.findAnimalsByName("Lion")).thenReturn((a1));
		
		//act + assert step
		assertEquals(a1, userv.findAnimalsByName("Lion"));
	}
	
	@Test
	@Order(8)
	@DisplayName("8. Unneccessay/Unused Test")
	@Disabled("Disabled until CreateUser is up!") 
	// @Disabled will allow you to ignore this test while debugging other tests
	public void unusedTest() {
		return;
	}
}