package com.topyougo.productimport.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.topyougo.productimport.component.UtilityClass;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.repository.OrderRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	OrderRepository orderRepository;
	
	@MockBean
	private Orders order;

	@BeforeEach
	public void setup() {
		logger.info("@BeforeAll - executes once before all test methods in this class");
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		order = new Orders();
		order.setFirstName("Mc Kinley");
		order.setLastName("Tolentino");
	}

	@BeforeEach
	void init() {
		logger.info("@BeforeEach - executes before each test method in this class");
		System.out.println(">>>>>>>>>>>>>> hereeeeeeeeeeee");
	}

	@Test
	public void contextLoads() throws Exception {
		
		List<Orders> ordersList = new ArrayList<Orders>();
		ordersList.add(order);
		
		when(orderRepository.findAllByOrderByOrderIDDesc()).thenReturn(ordersList);
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/fetchRecords").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		System.out.println(UtilityClass.toJson(result.getResponse().getContentAsString()));
		
		verify(orderRepository, times(1)).findAllByOrderByOrderIDDesc();
	}
}
