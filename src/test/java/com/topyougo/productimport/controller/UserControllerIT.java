package com.topyougo.productimport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.topyougo.productimport.repository.UserRepository;
import com.topyougo.productimport.repository.UserRolesRepository;
import com.topyougo.productimport.security.JwtAuthEntryPoint;
import com.topyougo.productimport.security.JwtProvider;
import com.topyougo.productimport.service.impl.UserDetailsServiceImpl;

@WebMvcTest(UserController.class)
public class UserControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userService;

	@MockBean
	private UserRolesRepository userRolesRepository;
	
	@MockBean
	private UserDetailsServiceImpl userDetailService;
	
	@MockBean
	private JwtProvider jwtProvider;
	
	@MockBean
	private JwtAuthEntryPoint entryPoint;
}
