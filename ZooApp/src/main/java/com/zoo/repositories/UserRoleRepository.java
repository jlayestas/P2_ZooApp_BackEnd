package com.zoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.zoo.models.UserRole;


@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	//RETURNING specifies what value this query will return
	//Any query that is attached to a non-void function must return the appropriate value
	//for this one and the delete, they return true to match the boolean value of their function
	@Query(value="UPDATE user_roles SET r_role=?1 WHERE r_id=?2 RETURNING true", nativeQuery = true)
	public boolean update(String role, int id);
	
	@Query(value="SELECT * FROM user_roles WHERE r_id=?1", nativeQuery = true)
	public UserRole findById(int id);
	
	@Query(value="DELETE FROM user_roles WHERE r_id=?1 RETURNING true", nativeQuery = true)
	public boolean delete(int id);
}
