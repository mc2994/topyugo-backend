package com.topyougo.productimport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard1;
import com.topyougo.productimport.model.Dashboard2;
import com.topyougo.productimport.repository.Dashboard2Repository;
import com.topyougo.productimport.repository.DashboardRepository;
import com.topyougo.productimport.service.impl.DashboardServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceUT {

	@InjectMocks
	private DashboardServiceImpl service;

	@Mock
	private DashboardRepository repository;

	@Mock
	private Dashboard2Repository dashboard2Repo;
	

	@Test
	public void testGetDashboardService() throws Exception {
		Dashboard1 model = new Dashboard1();
		model.setDelivered("999.00");
		model.setId(1L);
		model.setMonth("August");
		
		when(repository.getDashboard1("08", "2020")).thenReturn(model);
		
		Dashboard1DTO dto = service.getDashboardService("08", "2020");

		assertEquals(dto.getMonth(), model.getMonth());
		assertEquals(dto.getDelivered(), model.getDelivered());
		assertEquals(dto.getId(), model.getId());
	}

	@Test
	public void testGetDashboard2Service() throws Exception {
		Dashboard2 model = new Dashboard2();
		model.setAmount("250");
		model.setTotalOrders("25");
		model.setMonth(8);
		
		when(dashboard2Repo.getDashboard2("08", "2020")).thenReturn(model);
		
		Dashboard2DTO dto = service.getDashboard2Service("08", "2020");
		
		assertEquals(model.getAmount(), dto.getAmount());
		assertEquals(model.getTotalOrders(), dto.getTotalOrders());
		assertEquals(model.getMonth(), dto.getMonth());
	}
}