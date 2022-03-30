package com.topyougo.productimport.service;

import java.util.List;

import com.topyougo.productimport.dto.ProductsDTO;

public interface FileDownloadService {

    abstract byte[] productsToExcel(List<ProductsDTO> productsList);

}
