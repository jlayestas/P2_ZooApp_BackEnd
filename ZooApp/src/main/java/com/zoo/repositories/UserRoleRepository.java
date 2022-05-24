package com.zoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.zoo.models.UserRole;


@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {


	@Query(value="UPDATE user_roles SET u_role=?1 r_id=?2", nativeQuery = true)
	public boolean update(String role, int id);
	
	@Query(value="SELECT * FROM user_roles where r_id=?1", nativeQuery = true)
	public UserRole findById(int id);
	
	@Query(value="DELETE FROM user_roles WHERE r_id=?1", nativeQuery = true)
	public boolean delete(int id);
}