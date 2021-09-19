package com.topyougo.productimport.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.topyougo.productimport.constant.SqlQuery;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.modelmapper.OrderEntityMapper;
import com.topyougo.productimport.modelmapper.OrderMapper;
import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.service.OrderService;
import com.topyougo.productimport.util.DateUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate; 
	
	@Override
	public ProductsDTO findOrdersByOrderNoAndProduct(String orderNo, String product) {
		Orders result = orderRepository.findOrdersByOrderNoAndProduct(orderNo, product);
		return OrderEntityMapper.convertOrderToProduct(result);
	}

	@Override
	public ProductsDTO findOrdersByOrderNo(String orderNo) {
		Orders result = orderRepository.findOrdersByOrderNo(orderNo);
		return OrderEntityMapper.convertOrderToProduct(result);
	}

	@Override
	public ProductsDTO findOrdersByTrackingNumber(String trackingNumber) {
		Orders result = orderRepository.findOrdersByTrackingNumber(trackingNumber);
		return OrderEntityMapper.convertOrderToProduct(result);
	}

	@Override
	public List<ProductsDTO> findAllByOrderByOrderIDDesc() {
		List<Orders> productsList = orderRepository.findAllByOrderByOrderIDDesc();
		
		if(CollectionUtils.isEmpty(productsList)) {
			throw new EntityNotFoundException("No records found");
		}		
				
		return productsList.stream()
						   .map(OrderEntityMapper::convertOrderToProduct)
						   .collect(Collectors.toList());
	}

	@Override
	public List<ProductsDTO> findOrdersByDateOrdered(Date orderDate) {
		List<Orders> productsList = orderRepository.findOrdersByDateOrdered(orderDate);
		
		if(CollectionUtils.isEmpty(productsList)) {
			throw new EntityNotFoundException("No records found");
		}	 

		return productsList.stream()
							  .map(OrderEntityMapper::convertOrderToProduct)
							  .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ProductsDTO saveOrder(ProductsDTO productsDTO) {
		Orders order = OrderEntityMapper.convertProductToOrders(productsDTO);
		order = orderRepository.save(order);
		return OrderEntityMapper.convertOrderToProduct(order);
	}

	@Override
	public ProductsDTO findById(Long orderID) {
		Optional<Orders> order = orderRepository.findById(orderID);
		
		if(order.isEmpty()) {
			throw new EntityNotFoundException(String.format("Product with  id %s not found", orderID));
		}
		
		return OrderEntityMapper.convertOrderToProduct(order.get());
	}

	@Override
	public List<ProductsDTO> findOrdersByOrderDate(Map<String, Object> requestFilter, String userType) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		StringBuilder sqlQuery = new StringBuilder(SqlQuery.GET_ORDERS);

		if (!requestFilter.get("productname").equals("")) {
			sqlQuery.append("AND Product LIKE :product ");
			parameters.addValue("product", "%" + requestFilter.get("productname") + "%");
		}
		if (!requestFilter.get("orderStatus").equals("")) {
			sqlQuery.append("AND OrderStatus=:orderStatus ");
			parameters.addValue("orderStatus", requestFilter.get("orderStatus"));
		}
		if (requestFilter.get("orderDateFrom") != null && requestFilter.get("orderDateTo") != null) {
			sqlQuery.append("AND DateOrdered BETWEEN :orderDateFrom AND ");
			parameters.addValue("orderDateFrom", requestFilter.get("orderDateFrom"));

			sqlQuery.append(":orderDateTo ");
			parameters.addValue("orderDateTo", requestFilter.get("orderDateTo"));
		}
		parameters.addValue("userType", userType);
		sqlQuery.append("ORDER BY ID DESC");
		System.out.println("QUERY: " + sqlQuery.toString());
		List<ProductsDTO> result = jdbcTemplate.query(sqlQuery.toString(), parameters, new OrderMapper());
		return result;
	}

	@Override
	public List<ProductsDTO> fetchRecordsByUsertype(String userType) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userType", userType);

		String query = SqlQuery.GET_ORDERS + "AND DateOrdered='" + DateUtil.fortmatDateToString(new Date())
				+ "' ORDER BY ID DESC";

		List<ProductsDTO> productsList = jdbcTemplate.query(query, parameters, new OrderMapper());;
		return productsList;
	}

	@Override
	public ProductsDTO updateOrder(ProductsDTO product) {
		Orders updateOrder = OrderEntityMapper.convertProductToOrders(product);
		orderRepository.save(updateOrder);
		return product;
	}
}