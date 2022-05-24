package com.zoo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.models.HabitatType;
import com.zoo.repositories.HabitatTypeRepository;

@Service
@Transactional
public class HabitatTypeServiceImpl implements HabitatTypeService{

	@Autowired
	private HabitatTypeRepository tRepo;
	
	@Override
	public boolean createType(HabitatType habitatType) {
		int pk = tRepo.save(habitatType).getId();	
		return (pk > 0) ? true : false;
	}

	@Override
	public HabitatType getTypeByName(String name) {
		return tRepo.findByName(name);
	}

	@Override
	public List<HabitatType> getAllTypes() {
		return tRepo.findAll();
	}

	@Override
	public boolean updateType(HabitatType habitatType) {
		return tRepo.update(habitatType.getName(), habitatType.getId());
	}

	@Override
	public boolean deleteType(HabitatType habitatType) {
		return tRepo.delete(habitatType.getId());
	}

}
