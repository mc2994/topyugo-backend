package com.topyougo.productimport.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topyougo.productimport.component.OrderConverter;
import com.topyougo.productimport.component.SqlQuery;
import com.topyougo.productimport.dto.OrderMapper;
import com.topyougo.productimport.dto.OrdersMapper;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@GetMapping("/healthCheck")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<String>("Healthy", HttpStatus.OK);
	}

	@PostMapping("/order")
	public ResponseEntity<Orders> addOrder(@RequestBody ProductsDTO product) {
		Orders order = OrderConverter.convertProductToOrders(product);
		orderRepository.save(order);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}

	@PostMapping(value = "/order/{orderID}")
	public ResponseEntity<?> getOrderById(@RequestBody ProductsDTO product) {
		Optional<Orders> order = orderRepository.findById(product.getOrderID());
		if (!order.isPresent()) {
			return new ResponseEntity<String>("Order Id Not Found", HttpStatus.BAD_REQUEST);
		}
		ProductsDTO productDto = OrderConverter.convertOrderToProduct(order.get());
		return new ResponseEntity<ProductsDTO>(productDto, HttpStatus.OK);
	}

	@PutMapping("/order")
	public ResponseEntity<?> updateOrder(@RequestBody ProductsDTO product) {
		Optional<Orders> checkOrder = orderRepository.findById(product.getOrderID());
		
		if (!checkOrder.isPresent()) {
			return new ResponseEntity<String>("Order Id Not Found", HttpStatus.BAD_REQUEST);
		}
		
		Orders order = OrderConverter.convertProductToOrders(product);
		orderRepository.save(order);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@GetMapping("/fetchRecords")
	public ResponseEntity<List<ProductsDTO>> fetchRecords() {
		List<Orders> ordersList = orderRepository.findAllByOrderByOrderIDDesc();
		List<ProductsDTO> productsList = OrderConverter.convertModelToDTO(ordersList);
		return new ResponseEntity<List<ProductsDTO>>(productsList, HttpStatus.OK);
	}
	
	@GetMapping("/fetchRecords/{userType}")
	public ResponseEntity<List<ProductsDTO>> fetchRecordsByUsertype(@PathVariable("userType") String userType) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userType", userType);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String query = SqlQuery.GET_ORDERS+"AND DateOrdered='"+dateFormat.format(date)+ "' ORDER BY ID DESC";
		List<Orders> ordersList = jdbcTemplate.query(query, parameters, new OrdersMapper());
		List<ProductsDTO> productsList = OrderConverter.convertModelToDTO(ordersList);		
		return new ResponseEntity<List<ProductsDTO>>(productsList, HttpStatus.OK);
	}

	@PostMapping("/order/filter/{userType}")
	public ResponseEntity<?> getOrderByOrderDate(@RequestBody Map<String, Object> requestFilter,
			@PathVariable("userType") String userType) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		StringBuilder sqlQuery = new StringBuilder(SqlQuery.GET_ORDERS);
		
		if (!requestFilter.get("productname").equals("")) {
			sqlQuery.append("AND Product LIKE :product ");
			parameters.addValue("product", "%"+requestFilter.get("productname")+"%");
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
		System.out.println("QUERY: "+sqlQuery.toString());
		List<ProductsDTO> result = jdbcTemplate.query(sqlQuery.toString(), parameters, new OrderMapper());
		return new ResponseEntity<List<ProductsDTO>>(result, HttpStatus.OK);
	}
}