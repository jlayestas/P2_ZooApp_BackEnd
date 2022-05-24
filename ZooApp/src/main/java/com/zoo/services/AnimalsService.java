package com.revature.services;

import java.util.List;

import com.revature.models.Animals;

public interface AnimalsService {
	
		//create a new animal
		boolean createAnimal(Animals animals);
		//get animal by id
		Animals getAnimalsById(int id);
		//get all animals
		List<Animals> getAllAnimals();
		//update an animal
		boolean updateAnimals(Animals animals);
		//delete an animal
		boolean deleteAnimals(Animals animals);

}
