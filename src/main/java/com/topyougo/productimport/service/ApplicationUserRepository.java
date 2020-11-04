package com.topyougo.productimport.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topyougo.productimport.model.User;

@Repository
public interface ApplicationUserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
