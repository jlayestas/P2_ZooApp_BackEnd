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
import com.zoo.controller.HabitatTypeController;
import com.zoo.models.HabitatType;
import com.zoo.repositories.HabitatTypeRepository;
import com.zoo.services.HabitatTypeService;
import com.zoo.services.HabitatTypeServiceImpl;
import com.zoo.util.ClientMessageUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HabitatTypeController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HabitatTypeControllerTest {
	 
	private static HabitatType mockHType1;
	private static HabitatType mockHType2;
	private static HabitatType mockHTypeCreation;
	private static HabitatType mockHTypeModification;
	private static HabitatType mockHTypeDeletion;
	private static List<HabitatType> dummyDb;
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	HabitatTypeController HController;

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private HabitatTypeService htserv;

	@SuppressWarnings("deprecation")
	public boolean isValidJSON(final String json) {
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
		mockHType1 = new HabitatType(1, "Africa");
		mockHType2 = new HabitatType(2, "Asia");
		
		mockHTypeCreation =  new HabitatType("South America");
		
		mockHTypeModification = mockHTypeCreation;
		mockHTypeModification = new HabitatType("South America");
		
		mockHTypeDeletion = new HabitatType(4, "Europe");
		
		dummyDb = new ArrayList<HabitatType>();
		dummyDb.add(mockHType1);
		dummyDb.add(mockHType2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(HController).isNotNull();
	}
	
//	@Test
//	@Order(2)
//	@DisplayName("2. Create Habitat Type - Happy Path Scenerio Test")
//	public void testCreateHType() throws Exception {
//		// id number of this creation should be 3
//		mockHTypeCreation = new HabitatType(3);
//		//tell Mockito the behavior that I want this method to act like in the mock environment
//		when(htserv.createType(mockHTypeCreation)).thenReturn(true);;
//		
//		//act
//		RequestBuilder request = MockMvcRequestBuilders.post("/api/habitatType/createName?")
//				.accept(MediaType.APPLICATION_JSON_VALUE)
//				.content(om.writeValueAsString(mockHTypeCreation))
//				.contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mockmvc.perform(request).andReturn();
//		//assert
//		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
//				result.getResponse().getContentAsString());
//	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get User Role by ID - Happy Path Scenerio Test")
	public void testGetTypeByName() throws Exception {
		when(htserv.getTypeByName("Africa")).thenReturn(mockHType1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/habitatType/type?name=Africa");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockHType1), result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get All Types - Happy Path Scenerio Test")
	public void testGetAllTypes() throws Exception {
		when(htserv.getAllTypes()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/habitatType/all");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(dummyDb), result.getResponse().getContentAsString());
	}
	
//	@Test
//	@Order(5)
//	@DisplayName("5. Update an Existing Type - Happy Path Scenerio Test")
//	// @Disabled("Disabled until CreateCandyTest is up!")
//	public void testUpdateType() throws Exception {
//		when(htserv.updateType(mockHTypeModification)).thenReturn(true);
//		RequestBuilder request = MockMvcRequestBuilders.put("/api/habitatType/UpdateType")
//				.accept(MediaType.APPLICATION_JSON_VALUE)
//				.content(om.writeValueAsString(mockHTypeModification))
//				.contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mockmvc.perform(request).andReturn();
//		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
//				result.getResponse().getContentAsString());
//	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete Type - Happy Path Scenerio Test")
	public void testDeleteType() throws Exception {
		when(htserv.deleteType(mockHTypeDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/habitatType/DeleteType")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockHTypeDeletion))
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
