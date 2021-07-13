package com.topyougo.productimport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.topyougo.productimport.constant.Courier;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.modelmapper.OrderEntityMapper;
import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.util.CSVHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File Upload", description = "Upload Tracking Number CSV File")
@RestController
@RequestMapping("/api")
public class ImportProductsController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderRepository orderRepository;
	
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(1);
	
	private List<Orders> ordersList = new ArrayList<Orders>();
	
	@Operation(summary = "Upload CSV File for Batch Update of Order",
		responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
	        @ApiResponse(description = "File format is not supported", responseCode = "401")})
	@PostMapping("/importexcel")
	public ResponseEntity<List<Orders>> importCSVFile(@RequestPart("file") MultipartFile file) {
		
		if(!CSVHelper.hasCSVFormat(file)){
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "File format is not supported");
		}
		
		List<Orders> ordersList = new ArrayList<Orders>();
		try {
			ordersList = readCSVFile(file);
			saveOrdersAsync(ordersList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}
	
	private void saveOrdersAsync(List<Orders> orders) {
		CompletableFuture.runAsync(() -> {
			ordersList = new ArrayList<Orders>();
			ordersList = orderRepository.saveAll(orders);
		});
	}
	
	@Operation(summary = "Check File upload status",
		responses = {
		      @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json"))})
	@GetMapping("/checkThreadStatus")
	public ResponseEntity<List<Orders>> checkThreadStatus() {
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}
	
	@Operation(summary = "Upload CSV File Tracking Number", 
		responses = {
		      @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
		      @ApiResponse(description = "File format is not supported", responseCode = "401")})
	@PostMapping("/importtrackingno")
	public ResponseEntity<List<Orders>> importCsvTrackingFile(@RequestPart("file") MultipartFile file) {
		List<Orders> ordersList = new ArrayList<Orders>();
		try {
			ordersList = readCSVTrackingFile(file);
			for(Orders product : ordersList) {
				Orders order = orderRepository.findOrdersByOrderNo(product.getOrderNo());
				order.setTrackingNumber(product.getTrackingNumber());
				order.setCourier(Courier.getValueOf(product.getCourier().getValue()));
				orderRepository.save(order);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}
	
	private List<Orders> readCSVFile(MultipartFile excelFile) {
		try {
			List<ProductsDTO> productList = CSVHelper.csvToOrders(excelFile.getInputStream());	
			return OrderEntityMapper.convertDTOToModel(productList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Orders>();
	}
	
	private List<Orders> readCSVTrackingFile(MultipartFile excelFile) {
		try {
			List<ProductsDTO> productList = CSVHelper.csvTrackingUpdate(excelFile.getInputStream());
			return OrderEntityMapper.convertDTOToModel(productList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Orders>();
	}
}