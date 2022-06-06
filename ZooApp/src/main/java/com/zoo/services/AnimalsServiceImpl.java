package com.zoo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.models.Animals;
import com.zoo.repositories.AnimalsRepository;

@Service
@Transactional
public class AnimalsServiceImpl implements AnimalsService {
	
	@Autowired
	private AnimalsRepository arepo;
	
	private static Logger log = Logger.getLogger(AnimalsServiceImpl.class);
	
	@Override
	public boolean createAnimal(Animals animals) {
		log.info("In service layer, creating a new animal: " + animals);
		
		int pk = arepo.save(animals).getId();
		return (pk > 0) ? true : false;
		
	}

	@Override
	public Animals getAnimalsById(int id) {;
	
	log.info("In service layer, getting animal by id:" + id);
	
				System.out.println("ID: " + id);
				return arepo.findById(id);
	
	}

	@Override
	public List<Animals> getAllAnimals() {
		
		log.info("In service layer, getting list of all Animals");
		
		return arepo.findAll();
	}

	@Override
	public boolean updateAnimals(Animals animals) {
		
		log.info("In service layer, updating an animal: " + animals);
		
		Animals target = arepo.findById(animals.getId());
		target.setName(animals.getName());
		target.setLifespan(animals.getLifespan());
		target.setDiet(animals.getDiet());
		return (arepo.save(target) != null) ? true : false;
	}

	@Override
	public boolean deleteAnimals(Animals animals) {
		
		log.info("In service layer, deleting an animal: " + animals);
		
		arepo.delete(animals);
		return true;
	}

}
