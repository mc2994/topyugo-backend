package com.topyougo.productimport.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.topyougo.productimport.constant.Status;
import com.topyougo.productimport.dto.UserDTO;
import com.topyougo.productimport.model.RoleName;
import com.topyougo.productimport.model.User;
import com.topyougo.productimport.model.UserRoles;
import com.topyougo.productimport.modelmapper.UserEntityMapper;
import com.topyougo.productimport.repository.UserRepository;
import com.topyougo.productimport.repository.UserRolesRepository;
import com.topyougo.productimport.util.JsonUtil;

public class UserControllerIT extends BaseControllerTestConfig {
	
	@Mock
	private UserRepository userService;

	@Mock
	private UserRolesRepository userRolesRepository;
	
	@Test
	public void createUser() throws Exception {
		UserDTO user = new UserDTO();
		user.setEmail("sdsad@email.com");
		user.setFirstName("first");
		user.setLastName("last");
		user.setUsername("aaaaa");
		user.setPassword("password123");
		user.setUserType(RoleName.ADMIN.getValue());
		user.setStatus(Status.ACTIVE.getValue());
		
		UserRoles roles = new UserRoles();
		roles.setRoleId(1);
		roles.setUserId(2);
		
		when(userService.save(UserEntityMapper.mapDTOToModel(user))).thenReturn(UserEntityMapper.mapDTOToModel(user));
		when(userRolesRepository.save(roles)).thenReturn(roles);
		
		MvcResult result =  mockMvc.perform(post("/api/register")
				.content(JsonUtil.toJson(user))
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getAllUsers() throws Exception{
		User user  = new User();
		user.setEmail("hello@email.com");
		user.setFirstName("first");
		user.setLastName("last");
		
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		
		when(userService.findAll()).thenReturn(userList);
		
		MvcResult result = mockMvc.perform(get("/api/allusers")
			   .contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
}
