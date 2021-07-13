package com.topyougo.productimport.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;

@WebMvcTest(value=HomeController.class)
class HomeControllerUT {
	
	
	@MockBean
	private UserDetailsService userDetailsService;

	@Test
	final void testHealthCheck() {
		
	}

}
