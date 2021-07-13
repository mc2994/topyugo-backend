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
import com.topyougo.productimport.dto.Dashboard1DTO;
import com.topyougo.productimport.dto.Dashboard2DTO;
import com.topyougo.productimport.model.Dashboard5;
import com.topyougo.productimport.repository.Dashboard5Repository;
import com.topyougo.productimport.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Dashboard", description = "Display all dashboard for Admin")
@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/api")
public class DashboardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private Dashboard5Repository dashboard5Service;

	@Operation(summary = "Get details for dashboard 1", responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@GetMapping("/fetchDashboard1")
	public ResponseEntity<?> fetchDashboard1(
			@Parameter(description = "Month to fetch order ie. 08 - August") @RequestParam("month") String month,
			@Parameter(description = "Year to fetch order") @RequestParam("year") String year) {

		logger.info("Fetching dashboard 1 records...");
		Dashboard1DTO result = dashboardService.getDashboardService(month, year);

		return new ResponseEntity<Dashboard1DTO>(result, HttpStatus.OK);
	}

	@Operation(summary = "Get details for dashboard 5", responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@GetMapping("/fetchDashboard5")
	public ResponseEntity<?> fetchDashboard5(
				@Parameter(description = "Month to fetch order ie. 08 - August") @RequestParam("month") String month, 
				@Parameter(description = "Year to fetch order") @RequestParam("year") String year) {

		logger.info("Fetching dashboard 5 records...");
		List<Dashboard5> result = dashboard5Service.getDashboard5(month, year);
		return new ResponseEntity<List<Dashboard5>>(result, HttpStatus.OK);
	}

	@Operation(summary = "Get details for dashboard 2", responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@GetMapping("/fetchDashboard2")
	public ResponseEntity<?> fetchDashboard2(
			@Parameter(description = "Month to fetch order ie. 08 - August") @RequestParam("month") String month, 
			@Parameter(description = "Year to fetch order") @RequestParam("year") String year) {

		logger.info("Fetching dashboard 2 records...");
		Dashboard2DTO result = dashboardService.getDashboard2Service(month, year);

		return new ResponseEntity<Dashboard2DTO>(result, HttpStatus.OK);
	}
}