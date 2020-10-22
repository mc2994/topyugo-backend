package com.topyougo.productimport.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topyougo.productimport.component.OrderConverter;
import com.topyougo.productimport.component.UtilityClass;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.service.FileDownloadService;

@RestController
@RequestMapping("/api")
public class FileController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderRepository orderService;
	
	@Autowired
	private FileDownloadService fileDownloadService;

	@PostMapping("/downloadExcel")
	public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody List<ProductsDTO> ordersList) {
		//List<Orders> ordersList = this.orderService.findAll();
		//List<ProductsDTO> productsList = OrderConverter.convertModelToDTO(ordersList);
		ByteArrayResource resource = null;
		byte[] excelFile = null;
		HttpHeaders headers = null;
		try {
			System.out.println(UtilityClass.toJson(ordersList));
			excelFile = fileDownloadService.productsToExcel(ordersList);
			headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment;filename=order_file.xlsx");
			resource = new ByteArrayResource(excelFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.ok().contentLength(excelFile.length)
				.contentType(MediaType.parseMediaType(
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
						)).headers(headers).body(resource);
	}
}
