package com.topyougo.productimport.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topyougo.productimport.model.Role;
import com.topyougo.productimport.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName name);
}