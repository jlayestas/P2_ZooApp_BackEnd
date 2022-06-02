package com.zoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.models.HabitatType;

@Repository
@Transactional
public interface HabitatTypeRepository extends JpaRepository<HabitatType, Integer>{
	
	@Query(value="UPDATE habitat_type SET t_name=?1 t_id=?2", nativeQuery = true)
	public boolean update(String name, int id);
	
	@Query(value="SELECT * FROM habitat_type where t_name=?1", nativeQuery = true)
	public HabitatType findByName(String name);
	
	@Query(value="DELETE FROM habitat_type WHERE t_id=?1 RETURNING true", nativeQuery = true)
	public boolean delete(int id);

}
