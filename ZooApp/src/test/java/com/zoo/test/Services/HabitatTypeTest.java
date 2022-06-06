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

import com.zoo.models.HabitatType;
import com.zoo.repositories.HabitatTypeRepository;
import com.zoo.services.HabitatTypeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HabitatTypeTest {
	
	@Mock
	private static HabitatTypeRepository typeRepo;
	@InjectMocks
	private static HabitatTypeServiceImpl service;
	
	private HabitatType type = new HabitatType(2, "South America");
	private HabitatType updateHType = new HabitatType(1, "Sou");
	private HabitatType notAType = new HabitatType(3,"Bad Data");
	
	List<HabitatType> mockDB = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		mockDB.add(type);
		
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(typeRepo.findByName(type.getName())).thenReturn(type);
		Mockito.when(typeRepo.findAll()).thenReturn(mockDB);
		Mockito.when(typeRepo.save(type)).thenReturn(type);
		Mockito.when(typeRepo.update(updateHType.getName(), updateHType.getId())).thenReturn(true);
//		Mockito.when(typeRepo.update(notAType.getName(), updateHType.getId())).thenReturn(false);
		Mockito.when(typeRepo.delete(type.getId())).thenReturn(true);
		Mockito.when(typeRepo.delete(notAType.getId())).thenReturn(false);
	}
	
	@Test
	public void createHabitatTypeTest() {
		assertTrue(service.createType(type));
	}
	
	@Test
	public void getType() {
		assertEquals(type, service.getTypeByName(type.getName()));
	}
	
	@Test
	public void getBadData() {
		assertEquals(null, service.getTypeByName(notAType.getName()));
	}
	
	@Test
	public void getAll() {
		assertEquals(mockDB, service.getAllTypes());
	}
	
	@Test
	public void updateHabitatType() {
		assertEquals(true, service.updateType(updateHType));
	}
	
	@Test
	public void updateBadData() {
		assertEquals(false, service.updateType(notAType));
	}
	
	@Test
	public void deleteType() {
		assertEquals(true, service.deleteType(type));
	}
	
	@Test
	public void deleteBadData() {
		assertEquals(false, service.deleteType(notAType));
	}
}
