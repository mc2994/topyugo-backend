package com.topyougo.productimport.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.repository.ShopifyStoreRepository;

@WebMvcTest(ShopifyIntegrationController.class)
class ShopifyIntegrationControllerIT {

	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private ShopifyStoreRepository shopifyRepository;
	
	@MockBean
	private RestTemplate restTemplate;	
	
	@Test
	void testShopifyWebhook() {
		
	}

	@Test
	void testAddOrder() {
		
	}

}
