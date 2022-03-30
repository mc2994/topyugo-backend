package com.topyougo.productimport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard1;
import com.topyougo.productimport.model.Dashboard2;
import com.topyougo.productimport.modelmapper.Dashboard1EntityMapper;
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
	return Dashboard1EntityMapper.convertModelToDTO(result);
    }

    @Override
    public Dashboard2DTO getDashboard2Service(String month, String year) {
	Dashboard2 result = dashboard2Service.getDashboard2(month, year);
	return Dashboard1EntityMapper.convertModelToDTO(result);
    }

}
