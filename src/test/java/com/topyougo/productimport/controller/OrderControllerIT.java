package com.topyougo.productimport.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.topyougo.productimport.constant.Courier;
import com.topyougo.productimport.constant.OrderStatus;
import com.topyougo.productimport.constant.SecurityConstants;
import com.topyougo.productimport.constant.TrackingStatus;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.modelmapper.OrderEntityMapper;
import com.topyougo.productimport.security.JwtAuthEntryPoint;
import com.topyougo.productimport.security.JwtProvider;
import com.topyougo.productimport.service.OrderService;
import com.topyougo.productimport.service.impl.UserDetailsServiceImpl;
import com.topyougo.productimport.util.JsonUtil;

@WebMvcTest(OrderController.class)
public class OrderControllerIT {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private OrderService orderService;
	
	@MockBean
	private UserDetailsServiceImpl userDetailService;
	
	@MockBean
	private JwtProvider jwtProvider;
	
	@MockBean
	private JwtAuthEntryPoint entryPoint;
	

//	@BeforeEach
//	public void setup() {
//		mockMvc = MockMvcBuilders
//				.webAppContextSetup(context)
//				//.defaultRequest(get("/").with(user("user").roles("ADMIN")))
//				.apply(springSecurity()).build();
//	}
	
	@Test
	@DisplayName("Add single order to mocked bean")
	@WithMockUser(username = "user", password = "pass", roles = "ADMIN")
	public void addOrder() throws Exception{
		Orders order = createOrder();
		
		when(orderService.saveOrder(OrderEntityMapper.convertOrderToProduct(order)))
				.thenReturn(OrderEntityMapper.convertOrderToProduct(order));
		
		mockMvc.perform(post("/api/order/")
				.content(JsonUtil.toJson(order))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.orderNo").value("GRT-27818"))
				.andExpect(jsonPath("$.address").value("J.P. Laurel St. Nasugbu, Batangas"))				
				.andExpect(status().isCreated());		
	}
	
	@Test
	@DisplayName("Fetch order using order Id")
	@WithMockUser(username = "user", password = "pass", roles = "ADMIN")
	public void getOrderById() throws Exception {	
		Orders order = createOrder();
				
		when(orderService.findById(order.getOrderID()))
				.thenReturn(OrderEntityMapper.convertOrderToProduct(order));
		
		 mockMvc.perform(get("/api/order/{orderID}", 247)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.orderID").value("247"))		
				.andExpect(jsonPath("$.orderNo").value("GRT-27818"))
				.andExpect(jsonPath("$.orderStatus").value("DELIVERED"))	
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Update order by Order Id")
	@WithMockUser(username = "user", password = "pass", roles = "ADMIN")
	public void testUpdateOrder() throws Exception {	
		Orders order = createOrder();
				
		when(orderService.findById(order.getOrderID()))
				.thenReturn(OrderEntityMapper.convertOrderToProduct(order));
		
		order.setAmount(250.00);
		order.setOrderStatus(OrderStatus.CANCELLED);
		
		when(orderService.saveOrder(OrderEntityMapper.convertOrderToProduct(order)))
			.thenReturn(OrderEntityMapper.convertOrderToProduct(order));
		
		 mockMvc.perform(put("/api/order/")
				.content(JsonUtil.toJson(order))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.orderID").value("247"))
				.andExpect(jsonPath("$.amount").value(250.00))
				.andExpect(jsonPath("$.orderStatus").value("CANCELLED"))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Fetch all orders")
	@WithMockUser(username = "user", password = "pass", roles = {SecurityConstants.SECRET})
	public void fetchRecords() throws Exception {
		Orders order = createOrder();

		List<ProductsDTO> productsList = new ArrayList<ProductsDTO>();
		productsList.add(OrderEntityMapper.convertOrderToProduct(order));
		
	    when(orderService.findAllByOrderByOrderIDDesc()).thenReturn(productsList);
	    
		mockMvc.perform(get("/api/order/fetchRecords")
			  .accept(MediaType.APPLICATION_JSON_VALUE))								   
              .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].orderNo").value("GRT-27818"))
              .andExpect(jsonPath("$[0].address").value("J.P. Laurel St. Nasugbu, Batangas"));
	}
	
	@Test
	@DisplayName("Fetch order using User Type")
	@WithMockUser(username = "user", password = "pass", roles = "ADMIN")
	public void fetchRecordsByUsertype() throws Exception {	
		Orders order = createOrder();
		String userType = "ADMIN";
		
		List<ProductsDTO> productsList = new ArrayList<ProductsDTO>();
		productsList.add(OrderEntityMapper.convertOrderToProduct(order));
		
		when(orderService.fetchRecordsByUsertype(userType))
				.thenReturn(productsList);
		
		 mockMvc.perform(get("/api/order/fetchRecords/{userType}", userType)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		 		.andExpect(jsonPath("$[0].orderNo").value("GRT-27818"))
		 		.andExpect(jsonPath("$[0].address").value("J.P. Laurel St. Nasugbu, Batangas"))	
				.andExpect(status().isOk());
	}
	
	private Orders createOrder() {
		Orders order = new Orders();
		order.setOrderID(247l);
		order.setOrderNo("GRT-27818");
		order.setAddress("J.P. Laurel St. Nasugbu, Batangas");
		order.setOrderStatus(OrderStatus.DELIVERED);	
		order.setCourier(Courier.JNT);
		order.setTrackingStatus(TrackingStatus.DELIVERED);
		
		return order;
	}
}
