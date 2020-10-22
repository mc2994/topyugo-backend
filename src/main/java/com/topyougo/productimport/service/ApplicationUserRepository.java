package com.topyougo.productimport.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.topyougo.productimport.model.User;

public interface ApplicationUserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
