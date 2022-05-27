package com.zoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.models.Animals;

@Repository
@Transactional
public interface AnimalsRepository extends JpaRepository<Animals, Integer>{

	@Query(value="UPDATE animals SET a_name=?1, a_lifespan=?2, a_diet=?3 WHERE a_id=?4", nativeQuery = true)
	public boolean update(String name, double lifespan, String diet, int id);
	
	@Query(value="SELECT * FROM animals where a_id=?1", nativeQuery = true)
	public Animals findById(int id);
	
}
