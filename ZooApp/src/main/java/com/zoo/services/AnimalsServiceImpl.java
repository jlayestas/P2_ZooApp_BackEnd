package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Animals;
import com.revature.repositories.AnimalsRepository;

@Service
@Transactional
public class AnimalsServiceImpl implements AnimalsService {
	
	@Autowired
	private AnimalsRepository arepo;
	
	@Override
	public boolean createAnimal(Animals animals) {
		int pk = arepo.save(animals).getId();
		return (pk > 0) ? true : false;
		
	}

	@Override
	public Animals getAnimalsById(int id) {;
				System.out.println("ID: " + id);
				//return crepo.findById(id).stream().findFirst().get();
				return arepo.findById(id);
	
	}

	@Override
	public List<Animals> getAllAnimals() {
		return arepo.findAll();
	}

	@Override
	public boolean updateAnimals(Animals animals) {
		Animals target = arepo.findById(animals.getId());
		target.setName(animals.getName());
		target.setLifespan(animals.getLifespan());
		target.setDiet(animals.getDiet());
		return (arepo.save(target) != null) ? true : false;
	}

	@Override
	public boolean deleteAnimals(Animals animals) {
		arepo.delete(animals);
		return true;
	}

}
