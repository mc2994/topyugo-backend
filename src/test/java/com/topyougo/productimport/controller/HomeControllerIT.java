package com.topyougo.productimport.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


public class HomeControllerIT extends BaseControllerTestConfig {

	@Test
	final void testHealthCheck() throws Exception {
		 mockMvc.perform(get("/")
					.accept(MediaType.APPLICATION_JSON_VALUE))	
					.andExpect(status().isOk());
	}

}
