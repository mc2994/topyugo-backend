package com.topyougo.productimport.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard1;
import com.topyougo.productimport.model.Dashboard2;
import com.topyougo.productimport.repository.Dashboard2Repository;
import com.topyougo.productimport.repository.DashboardRepository;
import com.topyougo.productimport.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardRepository dashboardService;

	@Autowired
	private Dashboard2Repository dashboard2Service;

	@Override
	public Dashboard1DTO getDashboardService(String month, String year) {
		Dashboard1 result = dashboardService.getDashboard1(month, year);
		return convertModelToDTO(result);
	}

	private Dashboard1DTO convertModelToDTO(Dashboard1 model) {
		Dashboard1DTO dto = new Dashboard1DTO();
		if(model!=null) {
			dto.setId(model.getId());
			dto.setDelivered(model.getDelivered());
			dto.setDeliveredGoal(model.getDeliveredGoal());
			dto.setDeliveredPercentage(model.getDeliveredPercentage());
			dto.setMonth(model.getMonth());
			dto.setPaid(model.getPaid());
			dto.setSales(model.getSales());
			dto.setSalesGoal(model.getSalesGoal());
			dto.setSalesPercentage(model.getSalesPercentage());
			dto.setShipped(model.getShipped());
			dto.setShippedGoal(model.getShippedGoal());
			dto.setShippingPercentage(model.getShippingPercentage());
			String timeStamp = new SimpleDateFormat("MMMM dd, yyyy HH:mm a").format(Calendar.getInstance().getTime());
			dto.setCurrentDate(timeStamp);
		}
		return dto;
	}

	@Override
	public Dashboard2DTO getDashboard2Service(String month, String year) {
		Dashboard2 result = dashboard2Service.getDashboard2(month, year);
		return convertModelToDTO(result);
	}

	private Dashboard2DTO convertModelToDTO(Dashboard2 model) {
		Dashboard2DTO dto = new Dashboard2DTO();
		if (model!=null) {
			dto.setAcpp(model.getAcpp());
			dto.setAds(model.getAds());
			dto.setAmount(model.getAmount());
			dto.setAov(model.getAov());
			dto.setCaptureRate(model.getCaptureRate());
			dto.setMonth(model.getMonth());
			dto.setRoas(model.getRoas());
			dto.setTotalOrders(model.getTotalOrders());
			dto.setYear(model.getYear());
		}
		return dto;
	}

}