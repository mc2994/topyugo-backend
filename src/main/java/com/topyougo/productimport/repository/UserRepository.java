package com.topyougo.productimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topyougo.productimport.dto.UserDTO;
import com.topyougo.productimport.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsernameAndPassword(String userName, String password);
	
	User findByUsernameAndEmail(String userName, String email);
}