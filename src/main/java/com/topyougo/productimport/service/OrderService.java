package com.topyougo.productimport.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;

public interface OrderService {

	ProductsDTO findOrdersByOrderNoAndProduct(String orderNo, String product);
	
	ProductsDTO findOrdersByOrderNo(String orderNo);
	
	ProductsDTO findOrdersByTrackingNumber(String orderNo);
	
	List<ProductsDTO> findAllByOrderByOrderIDDesc();
	
	List<ProductsDTO> findOrdersByDateOrdered(Date orderDate);
	
	ProductsDTO saveOrder(ProductsDTO order);
	
	ProductsDTO updateOrder(ProductsDTO product);
	
	ProductsDTO findById(Long orderID);
	
	List<ProductsDTO> findOrdersByOrderDate(Map<String, Object> requestFilter, String userType);
	
	List<ProductsDTO> fetchRecordsByUsertype(String userType);
	
	/*
	 * @Query(nativeQuery=true, value=SqlQuery.FIND_ALL_COURIER) List<Orders>
	 * findAllByCourierOrderByIDDesc(@Param("courier") String courier);
	 */
}
