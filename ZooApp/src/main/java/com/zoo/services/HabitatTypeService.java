package com.zoo.services;

import java.util.List;

import com.zoo.models.HabitatType;

public interface HabitatTypeService {
	
	boolean createType (HabitatType habitatType);
	
	HabitatType getTypeByName(String name);
	
	List<HabitatType> getAllTypes();
	
	boolean updateType(HabitatType habitatType);
	
	boolean deleteType(HabitatType habitatType);

}
