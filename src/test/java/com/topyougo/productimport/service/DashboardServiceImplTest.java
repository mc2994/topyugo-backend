package com.topyougo.productimport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard1;
import com.topyougo.productimport.model.Dashboard2;
import com.topyougo.productimport.repository.Dashboard2Repository;
import com.topyougo.productimport.repository.DashboardRepository;
import com.topyougo.productimport.service.impl.DashboardServiceImpl;

@SpringBootTest
class DashboardServiceImplTest {
	
	private final static Logger logger = LoggerFactory.getLogger(DashboardServiceImplTest.class);

	@InjectMocks
	private DashboardServiceImpl service;
	
	@Mock
	private DashboardRepository repository;
	
	@Mock
	private Dashboard2Repository dashboard2Service;
	
	
	@BeforeAll
	static void beforeAllTest() {
		logger.debug("Run before all test cases");
		logger.info("Run before all test cases");
		logger.error("Run before all test cases");
		logger.warn("Run before all test cases");
		logger.trace("Run before all test cases");
	}
	
	
	@BeforeEach
	public void setup() {
		logger.debug("Run before each test cases");
	}
	
	
	@Test
	final void testGetDashboardService() {	
		
		Dashboard1 model = new Dashboard1();
		model.setDelivered("DELIVERED");
		model.setId(1L);
		model.setMonth("08");
		
		
		when(repository.getDashboard1("08", "2020")).thenReturn(model);
		
		Dashboard1DTO dto = service.getDashboardService("08", "2020");
		
		assertEquals(dto.getMonth(), model.getMonth());
		assertEquals(dto.getDelivered(), model.getDelivered());
		assertEquals(dto.getId(), model.getId());
		
		verify(repository, times(1)).getDashboard1("08", "2020");
	}

	@Test
	final void testGetDashboard2Service() {
		
		Dashboard2 model = new Dashboard2();
		model.setAmount("250");
		model.setTotalOrders("25");
		model.setMonth(8);
		
		when(dashboard2Service.getDashboard2("08", "2020")).thenReturn(model);
		
		Dashboard2DTO dto = service.getDashboard2Service("08", "2020");
		
		assertEquals(model.getAmount(), dto.getAmount());
		assertEquals(model.getTotalOrders(), dto.getTotalOrders());
		assertEquals(model.getMonth(), dto.getMonth());
		
		verify(dashboard2Service, times(1)).getDashboard2("08", "2020");
	}

}
