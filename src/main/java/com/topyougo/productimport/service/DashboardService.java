package com.topyougo.productimport.service;

import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;

public interface DashboardService {
	
	public Dashboard1DTO getDashboardService(String month, String year);
	
	public Dashboard2DTO getDashboard2Service(String month, String year);
}
