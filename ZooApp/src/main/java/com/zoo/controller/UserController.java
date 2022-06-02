package com.zoo.controller;

import static com.zoo.util.ClientMessageUtil.CREATION_FAILED;
import static com.zoo.util.ClientMessageUtil.CREATION_SUCCESSFUL;
import static com.zoo.util.ClientMessageUtil.DELETION_FAILED;
import static com.zoo.util.ClientMessageUtil.DELETION_SUCCESSFUL;
import static com.zoo.util.ClientMessageUtil.UPDATE_FAILED;
import static com.zoo.util.ClientMessageUtil.UPDATE_SUCCESSFUL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.models.Animals;
import com.zoo.models.ClientMessage;
import com.zoo.models.User;
import com.zoo.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
@Api(value= "UserRestController", description = "REST controller related to User Entities")
public class UserController {
	
	@Autowired
	private UserService userv;
	
	@ApiOperation(value="User login with username and password", notes="Provide username and password to login", response= User.class)
	@GetMapping(path = "/login")
	public @ResponseBody User userLogin(@RequestParam(value="username, password", name="username, password") String username, String password){
		return userv.login(username, password);
	}
	
	@ApiOperation(value="Find user by id number", notes="Provide an id to look up a specific user from the API", response = User.class)
	@GetMapping("/getUser")
	public @ResponseBody User getById(@RequestParam(value="id", name="id") int id) {
		System.out.println("TEST: " + userv.findUsernameById(id));
		return userv.findUsernameById(id);
	}
	
	@ApiOperation(value="Find animal by id", notes="Provide an animal name to look up a specific animal from the API", response = Animals.class)
	@GetMapping(path = "/getAnimal")
	public @ResponseBody Animals getAnimalName (@RequestParam(value="id", name="id") int AnimalId) {
		System.out.println("TEST: " + userv.findAnimalsById(AnimalId));
		return userv.findAnimalsById(AnimalId);
	}
	
	
	@PostMapping("/users")
	@ApiOperation("create new user entity")
	public @ResponseBody ClientMessage createAccount(@RequestBody User user) {
		return userv.createAccount(user) ? CREATION_SUCCESSFUL : CREATION_FAILED;
	}

	@PutMapping("/updateUser")
	@ApiOperation("update new user entity")
	public @ResponseBody ClientMessage editUser(@RequestBody User user) {
		return userv.editUser(user) ? UPDATE_SUCCESSFUL : UPDATE_FAILED;
	}

	@DeleteMapping("/deleteUser")
	@ApiOperation("delete user entity")
	public @ResponseBody ClientMessage deleteUser(@RequestBody User user) {
		return userv.deleteUser(user) ? DELETION_SUCCESSFUL : DELETION_FAILED;
	}

}
