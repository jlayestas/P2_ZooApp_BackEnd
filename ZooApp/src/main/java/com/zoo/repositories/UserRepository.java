package com.zoo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zoo.models.Animals;
import com.zoo.models.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value="UPDATE user SET u_first_name=?1, u_last_name=?2, u_email=?3, u_password=?4 WHERE u_id=?5 RETURNING true", nativeQuery=true)
	public boolean update(String firstName, String lastName, String email, String password, int id);
	
	@Query(value="SELECT * FROM users WHERE u_id=?1", nativeQuery=true)
	public User findById(int id);
	
	@Query(value="SELECT * FROM users WHERE u_username=?1 AND u_password=?2", nativeQuery=true)
	public User userLogin (String username, String password);
	
	@Query(value="SELECT * FROM animals name WHERE a_name=?1", nativeQuery=true)
	public Animals findAnimals(String Aname);
}

