package com.topyougo.productimport.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.repository.ShopifyStoreRepository;

class ShopifyIntegrationControllerIT extends BaseControllerTestConfig {

	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private ShopifyStoreRepository shopifyRepository;
	
	@Mock
	private RestTemplate restTemplate;	
	
	@Test
	void testShopifyWebhook() {
		
	}

	@Test
	void testAddOrder() {
		
	}

}
