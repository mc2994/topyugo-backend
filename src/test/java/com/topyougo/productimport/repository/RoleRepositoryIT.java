package com.topyougo.productimport.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.topyougo.productimport.model.Role;
import com.topyougo.productimport.model.RoleName;


class RoleRepositoryIT extends BaseRepositoryTestConfig{

	@Autowired
	private RoleRepository roleRepository;
	
	@Disabled
	@Test
	void testFindByName() {
		Role role = new Role();
		role.setId(2321321L);
		role.setName(RoleName.ADMIN);
		
		roleRepository.save(role);
		
		Role response = roleRepository.findByName(RoleName.ADMIN).get();
		
		assertNotNull(response);
		assertEquals(RoleName.ADMIN, response.getName());
	}

}
