package com.zoo.test.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.zoo.models.Animals;
import com.zoo.models.HabitatType;
import com.zoo.repositories.AnimalsRepository;
import com.zoo.services.AnimalsService;
import com.zoo.services.AnimalsServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimalsTest {
	
	@Mock
	private static AnimalsRepository arepo;
	
	@InjectMocks
	private static AnimalsServiceImpl aserv;
	
	private static Animals a1, a2;
	static List<Animals> dummyDb;
	

	@BeforeAll
	static void setUpBeforeClass() {
		
		arepo = Mockito.mock(AnimalsRepository.class);
		
//		aserv = new AnimalsServiceImpl(arepo);
		
		a1 = new Animals(1, "Lion", 50, "Carnivore", new HabitatType(1, "Africa"));
		a2 = new Animals(2, "Panther", 50, "Omnivore", new HabitatType(2, "South America"));
		
		dummyDb = new ArrayList<Animals>();
		dummyDb.add(a1);
		dummyDb.add(a2);
		
	}
	
	@Test
	@Order(1)
	@DisplayName("Create new Animal Test")
	void testCreateAnimal() {
		
		Animals a3 = new Animals("Zebra", 40, "Herbivore", new HabitatType(3, "Sub Africa"));
		a3.setId(3);
		
		
        when(arepo.save(a3)).thenReturn(a3);
        
		
		assertEquals(true, aserv.createAnimal(a3));

	}
	
	@Test
	@Order(2)
	@DisplayName("Get Animal by Id Test")
	void getAnimalByIdTest() {
		
		when(arepo.findById(1)).thenReturn(a1);
		
		assertEquals(a1, aserv.getAnimalsById(1));
			
	}
	
	@Test
	@Order(3)
	@DisplayName("Get all Animals Test")
	void testGetAllAnimals() {
		
		when(aserv.getAllAnimals()).thenReturn(dummyDb);
        
		
		assertEquals(dummyDb, aserv.getAllAnimals());
		
	}
	
	@Test
	@Order(4)
	@DisplayName("Update Animals Test")
	void testUpdateCandy() {
		a2.setName("Jorge");;
		a2.setDiet("Carnivore");
		
		when(aserv.getAnimalsById(2)).thenReturn(a2);
		when(arepo.save(a2)).thenReturn(a2);
		
		assertEquals(true, aserv.updateAnimals(a2));
		
	}

	@Test
	@Order(5)
	@DisplayName("Delete Animal Test")
	void testDeleteAnimal() {
		
		doNothing().when(arepo).delete(a2);
		
		assertEquals(true, aserv.deleteAnimals(a2));
		
	}
}
