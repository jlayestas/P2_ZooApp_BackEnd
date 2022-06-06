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
import com.zoo.controller.AnimalsController;
import com.zoo.models.Animals;
import com.zoo.models.HabitatType;
import com.zoo.repositories.AnimalsRepository;
import com.zoo.services.AnimalsService;
import com.zoo.services.AnimalsServiceImpl;
import com.zoo.util.ClientMessageUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AnimalsController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnimalControllerTest {
	
	private static Animals mockAnimal1;
	private static Animals mockAnimal2;
	private static Animals mockAnimalCreation;
	private static Animals mockAnimalModification;
	private static Animals mockAnimalDeletion;
	private static List<Animals> dummyDb;
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	AnimalsController animalController;
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private AnimalsService aserv;
	
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
		mockAnimal1 = new Animals(1, "Dragon", 100.00 ,"Canivore", new HabitatType(1, "Dungeon"));
		mockAnimal2 = new Animals(2, "Giraffe", 400.00 ,"Herbivore", new HabitatType(2, "Africa"));
		
		mockAnimalCreation = new Animals("Siberian Tiger", 35.00, "Canivore", new HabitatType(3, "Asia"));
		
		mockAnimalModification = mockAnimalCreation;
		mockAnimalModification.setName("Siberian Tiger");
		mockAnimalModification.setLifespan(35.00);
		mockAnimalModification.setDiet("Canivore");
		
		mockAnimalDeletion = new Animals(4, "Lion", 40.00, "Canivore", new HabitatType(2, "Africa"));
		
		dummyDb = new ArrayList<Animals>();
		dummyDb.add(mockAnimal1);
		dummyDb.add(mockAnimal2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(animalController).isNotNull();
	}	
	
	@Test
	@Order(2)
	@DisplayName("2. Create Animal - Happy Path Scenerio Test")
	public void testCreateAnimal() throws Exception {
		// id number of this creation should be 3
		mockAnimalCreation.setId(3);
		//tell Mockito the behavior that I want this method to act like in the mock environment
		when(aserv.createAnimal(mockAnimalCreation)).thenReturn(true);
		
		//act
		RequestBuilder request = MockMvcRequestBuilders.post("/api/animals")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockAnimalCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		//assert
		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	@Test
	@Order(3)
	@DisplayName("3. Get Animal by ID - Happy Path Scenerio Test")
	public void testGetAnimalById() throws Exception {
		when(aserv.getAnimalsById(1)).thenReturn(mockAnimal1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/id?id=1");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockAnimal1), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get All Animals - Happy Path Scenerio Test")
	public void testGetAllAnimals() throws Exception {
		when(aserv.getAllAnimals()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/animals");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(dummyDb), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update an Existing Animal - Happy Path Scenerio Test")
	// @Disabled("Disabled until CreateCandyTest is up!")
	public void testUpdateAnimal() throws Exception {
		when(aserv.updateAnimals(mockAnimalModification)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/animals")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockAnimalModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete Animal - Happy Path Scenerio Test")
	public void testDeleteAnimal() throws Exception {
		when(aserv.deleteAnimals(mockAnimalDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/animals")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockAnimalDeletion))
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
