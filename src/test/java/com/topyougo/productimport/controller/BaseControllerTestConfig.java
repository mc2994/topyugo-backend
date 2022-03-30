package com.topyougo.productimport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Profile("test")
@WithMockUser(username = "mc2994", password = "mckinley2994", authorities = { "ADMIN" })
public class BaseControllerTestConfig {

    @Autowired
    public MockMvc mockMvc;
    
}
