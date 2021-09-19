package com.topyougo.productimport.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order", description = "Orders Display")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/order")
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;
	
	
	@Operation(summary = "Save Order",
		responses = {
			@ApiResponse(responseCode = "201", description = "Order Created", 
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ProductsDTO.class))}),
			@ApiResponse(responseCode = "500", description = "Something went wrong")})
	@PostMapping("/user")
	public ResponseEntity<ProductsDTO> addOrder(@Valid @RequestBody ProductsDTO product) {
		
		orderService.saveOrder(product);
		
		return new ResponseEntity<ProductsDTO>(product, HttpStatus.CREATED);
	}

	@Operation(summary = "Fetch order by Id",
		responses = {
			@ApiResponse(description = "Order found", responseCode = "200", 
					content = @Content(mediaType = "application/json")),
	        @ApiResponse(description = "Order not found", responseCode = "404")})
	@GetMapping(value = "/{orderID}")
	public ResponseEntity<ProductsDTO> getOrderById(
				@Parameter(description = "id of order to be search") 
				@PathVariable(name = "orderID", required = true) Long orderID) {
		
		ProductsDTO productDto = orderService.findById(orderID);

		return new ResponseEntity<ProductsDTO>(productDto, HttpStatus.OK);
	}

	@Operation(summary = "Update Order",
		responses = {
			@ApiResponse(responseCode = "200", description = "Order updated", 
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ProductsDTO.class))}),
			@ApiResponse(responseCode = "500", description = "Internal server error")})
	@PutMapping("/")
	public ResponseEntity<ProductsDTO> updateOrder(@Valid @RequestBody ProductsDTO product) {
		ProductsDTO checkOrder = orderService.findById(product.getOrderID());
		orderService.saveOrder(checkOrder);
		return new ResponseEntity<ProductsDTO>(product, HttpStatus.OK);
	}

	@Operation(summary = "Fetch All Orders", 
		responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", 
				content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Not found")})
	@GetMapping("/fetchRecords")
	public ResponseEntity<List<ProductsDTO>> fetchRecords() {
		List<ProductsDTO> productsList = orderService.findAllByOrderByOrderIDDesc();
		
		return new ResponseEntity<List<ProductsDTO>>(productsList, HttpStatus.OK);
	}

	@Operation(summary = "Fetch order by user type",
		responses = {
			@ApiResponse(description = "Order found", responseCode = "200", 
				content = @Content(mediaType = "application/json")),
	        @ApiResponse(description = "Order not found", responseCode = "404")})
	@GetMapping("/fetchRecords/{userType}")
	public ResponseEntity<List<ProductsDTO>> fetchRecordsByUsertype(
								@Parameter(description = "User type to filter orders") 
								@PathVariable(value="userType",required = true) String userType) {
		
		List<ProductsDTO> productsList = orderService.fetchRecordsByUsertype(userType);
		
		return new ResponseEntity<List<ProductsDTO>>(productsList, HttpStatus.OK);
	}

	@Operation(summary = "Filter order by user defined paramerters",
		responses = {
			@ApiResponse(description = "Order found", responseCode = "200", 
				content = @Content(mediaType = "application/json")),
	        @ApiResponse(description = "Order not found", responseCode = "404")})
	@PostMapping("/filter/{userType}")
	public ResponseEntity<List<ProductsDTO>> getOrderByOrderDate(@RequestBody Map<String, Object> requestFilter,
			@PathVariable("userType") String userType) {

		List<ProductsDTO> result = orderService.findOrdersByOrderDate(requestFilter, userType);
		return new ResponseEntity<List<ProductsDTO>>(result, HttpStatus.OK);
	}
}