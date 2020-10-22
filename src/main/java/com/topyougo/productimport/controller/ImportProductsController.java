package com.topyougo.productimport.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.topyougo.productimport.component.CSVHelper;
import com.topyougo.productimport.component.OrderConverter;
import com.topyougo.productimport.component.UtilityClass;
import com.topyougo.productimport.dto.Courier;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class ImportProductsController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CSVHelper csvHelper;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(1);
	
	List<Orders> ordersList = new ArrayList<Orders>();
	
	@PostMapping("/importexcel")
	public ResponseEntity<List<Orders>> importCSVFile(@RequestPart("file") MultipartFile file) {
		List<Orders> ordersList = new ArrayList<Orders>();
		try {
			ordersList = readCSVFile(file);
			saveOrdersAsync(ordersList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}
	
	private void saveOrdersAsync(List<Orders> ordersListas) {
		CompletableFuture.runAsync(() -> {
			ordersList = new ArrayList<Orders>();
			ordersList = orderRepository.saveAll(ordersListas);
		});
	}
	
	@GetMapping("/checkThreadStatus")
	public ResponseEntity<List<Orders>> checkThreadStatus() {
		return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
	}
	
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
			List<ProductsDTO> productList = csvHelper.csvToOrders(excelFile.getInputStream());	
			return OrderConverter.convertDTOToModel(productList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Orders>();
	}
	
	private List<Orders> readCSVTrackingFile(MultipartFile excelFile) {
		try {
			List<ProductsDTO> productList = csvHelper.csvTrackingUpdate(excelFile.getInputStream());
			return OrderConverter.convertDTOTrackingToModel(productList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Orders>();
	}
}