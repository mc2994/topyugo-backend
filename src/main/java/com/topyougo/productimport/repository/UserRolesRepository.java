package com.topyougo.productimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.topyougo.productimport.model.UserRoles;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
	
}
