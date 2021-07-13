package com.topyougo.productimport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Application Health Check", description = "User Management API")
@RestController
public class HomeController {

	@Operation(summary = "Get Application health status", responses = {
		      @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
		      @ApiResponse(responseCode = "404", description = "Not found")})
	@GetMapping("/")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}