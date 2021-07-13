package com.topyougo.productimport.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.topyougo.productimport.dto.ProductsDTO;

public abstract class Report {

	public abstract byte[] getFile();
	
	public abstract void createReport(List<ProductsDTO> productsList);
	
	public abstract HttpHeaders getHeaders();
	
	public abstract String getMediaType();
}
