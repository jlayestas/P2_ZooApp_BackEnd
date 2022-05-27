package com.zoo.test.Services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.zoo.models.HabitatType;
import com.zoo.repositories.HabitatTypeRepository;
import com.zoo.services.HabitatTypeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HabitatTypeTest {
	
	@Mock
	private HabitatTypeRepository typeRepo;
	@InjectMocks
	private HabitatTypeServiceImpl service;
	
	private static HabitatType t1, t2;
	static List<HabitatType> dummyDb;
	
	@BeforeAll
	static void setUpBeforeClass() {
		typeRepo = Mockito.mock(HabitatTypeRepository.class);
		
		service = new HabitatTypeServiceImpl(typeRepo);
		
		t1 = new HabitatType(1, "Aferica");
		t2 = new HabitatType(2, "South America");
		
		dummyDb = new ArrayList<HabitatType>();
		dummyDb.add(t1);
		dummyDb.add(t2);
	}
	
	@Test
	@Order(1)
	@DisplayName("Create new HabitatType Test")
	void 
	

	

}
