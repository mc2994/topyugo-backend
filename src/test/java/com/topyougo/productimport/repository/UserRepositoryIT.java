package com.topyougo.productimport.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.topyougo.productimport.model.User;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryIT {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void testFindByUsernameAndPassword() {
		User user = new User();
		user.setEmail("test@email.com");
		user.setFirstName("Juan");
		user.setLastName("Dela Cruz");
		user.setStatus("Active");
		user.setUsername("123456");
		user.setPassword("789456123");
		
		userRepository.save(user);
		
		User response = userRepository.findByUsernameAndPassword("123456", "789456123");
		
		assertThat(response).isNotNull();
		assertEquals(user.getUsername(), response.getUsername());
		assertEquals(user.getPassword(), response.getPassword());
	}

	@Test
	void testFindByUsernameAndEmail() {
		User user = new User();
		user.setEmail("test@email.com");
		user.setFirstName("Juan");
		user.setLastName("Dela Cruz");
		user.setStatus("Active");
		user.setUsername("123456");
		user.setPassword("789456123");
		
		userRepository.save(user);
		
		User response = userRepository.findByUsernameAndEmail("123456", "test@email.com");
		
		assertThat(response).isNotNull();
		assertEquals(user.getUsername(), response.getUsername());
		assertEquals(user.getEmail(), response.getEmail());
	}

}
