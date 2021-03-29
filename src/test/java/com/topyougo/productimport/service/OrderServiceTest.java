package com.topyougo.productimport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.repository.OrderRepository;

@SpringBootTest
class OrderServiceTest {

	@Mock
	private OrderRepository repository;
	
	@DisplayName("Test Mock helloService + helloRepository")
	@Test
	final void test() {
		List<Orders> orders = new ArrayList<>();
		when(repository.findAll()).thenReturn(orders);
		
		assertEquals(0, repository.findAll().size());
	    verify(repository, times(1)).findAll();
	}

}
