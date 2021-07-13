package com.topyougo.productimport.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.service.FileDownloadService;
import com.topyougo.productimport.service.FileReportFactory;
import com.topyougo.productimport.service.Report;
import com.topyougo.productimport.util.JsonUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File Download", description = "Download report by type")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class FileDownloadController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileDownloadService fileDownloadService;

	@PostMapping("/downloadExcel")
	public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody List<ProductsDTO> ordersList) {
		ByteArrayResource resource = null;
		byte[] excelFile = null;
		HttpHeaders headers = null;
		try {
			System.out.println(JsonUtil.toJson(ordersList));
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
	
	@Operation(summary = "Download report based on the user request", security = @SecurityRequirement(name = "bearerAuth"), 
			responses = {
					@ApiResponse(description = "File Downloaed Successfully", responseCode = "200", 
							content = { @Content(mediaType = "text/csv or text/csv"), 
										@Content(mediaType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") 
									  }),
					@ApiResponse(responseCode = "404", description = "Not found")})
	@PostMapping("/downloadExcel/{reportType}")
	public ResponseEntity<ByteArrayResource> downloadReport(@RequestBody List<ProductsDTO> ordersList, 
			@PathVariable("reportType") String reportType ) {

		ByteArrayResource resource = null;
		Report report = null;
		try {			
			FileReportFactory reportFactory = new FileReportFactory();
			report = reportFactory.reportType(reportType);
			logger.info("Create report type of ",reportType);
			report.createReport(ordersList);
			
			resource = new ByteArrayResource(report.getFile());
			logger.info("Report generated...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.ok().contentLength(report.getFile().length)
				.contentType(MediaType.parseMediaType(
								report.getMediaType()))
								.headers(report.getHeaders())
								.body(resource);
	}
}
