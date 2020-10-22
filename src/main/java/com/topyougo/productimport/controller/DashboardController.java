package com.topyougo.productimport.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.topyougo.productimport.component.UtilityClass;
import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard5;
import com.topyougo.productimport.repository.Dashboard5Repository;
import com.topyougo.productimport.service.DashboardService;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/api")
public class DashboardController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private Dashboard5Repository dashboard5Service;
	

	@GetMapping("/fetchDashboard1")
	public ResponseEntity<?> fetchDashboard1(@RequestParam("month") String month,
			@RequestParam("year") String year) {
	
		Dashboard1DTO result = dashboardService.getDashboardService(month, year);
		
		System.out.println(UtilityClass.toJson(result));

		return new ResponseEntity<Dashboard1DTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/fetchDashboard5")
	public ResponseEntity<?> fetchDashboard5(@RequestParam("month") String month,
			@RequestParam("year") String year) {
	
		List<Dashboard5> result = dashboard5Service.getDashboard5(month, year);
		
		System.out.println(UtilityClass.toJson(result));

		return new ResponseEntity<List<Dashboard5>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/fetchDashboard2")
	public ResponseEntity<?> fetchDashboard2(@RequestParam("month") String month,
			@RequestParam("year") String year) {
	
		Dashboard2DTO result = dashboardService.getDashboard2Service(month, year);
		
		System.out.println(UtilityClass.toJson(result));

		return new ResponseEntity<Dashboard2DTO>(result, HttpStatus.OK);
	}
}
