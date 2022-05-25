package com.zoo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zoo.models.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value="UPDATE user SET u_first_name=?1, u_last_name=?2, u_email=?3 WHERE u_id=>4", nativeQuery=true)
	public boolean update(String firstName, String lastNAme, String Email, int id);
	
	@Query(value="SELECT * FROM username WHERE u_id=?1", nativeQuery=true)
	public User findById(int id);
	
	@Query(value="SELECT * FROM user WHERE u_username=?1, u_password=?2", nativeQuery=true)
	public User userLogin (String username, String password);
}

