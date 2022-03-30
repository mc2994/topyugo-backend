package com.topyougo.productimport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.modelmapper.OrderEntityMapper;
import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.service.impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
class OrderServiceUT{
	
	@InjectMocks
	private OrderServiceImpl service;
	
	@Mock
	private OrderRepository repository;
	
	
	@DisplayName("Test Mock helloService + helloRepository")
	@Test
	final void test() throws Exception {
		List<Orders> orders = new ArrayList<>();
		when(repository.findAll()).thenReturn(orders);
		
		assertEquals(0, repository.findAll().size());
	    verify(repository, times(1)).findAll();
	}

	@DisplayName("Find Order By Order No. and Product")
	@Test
	public void testFindOrdersByOrderNoAndProduct() {
		String orderNo = "order-001";
		String product = "product 1";
		Orders order = createOrder();
		
		when(repository.findOrdersByOrderNoAndProduct(orderNo, product)).thenReturn(order);
		
		service.findOrdersByOrderNoAndProduct(orderNo, product);
		
		verify(repository, times(1)).findOrdersByOrderNoAndProduct(orderNo, product);
	}
	
	@DisplayName("Find Order By Order No")
	@Test
	public void testFindOrdersByOrderNo() {
		String orderNo = "WHO-001";
		Orders order = createOrder();
		order.setOrderNo("WHO-001");
		
		when(repository.findOrdersByOrderNo(orderNo)).thenReturn(order);
		
		ProductsDTO response = service.findOrdersByOrderNo(orderNo);
		assertEquals(order.getOrderID(), response.getOrderID());
	}
	
	@DisplayName("Find Order By Tracking No")
	@Test
	public void testFindOrdersByTrackingNumber() {
		String trackingNumber = "JNT-0123123";
		Orders order = createOrder();
		order.setTrackingNumber("JNT-0123123");
		
		when(repository.findOrdersByTrackingNumber(trackingNumber)).thenReturn(order);
		
		ProductsDTO response = service.findOrdersByTrackingNumber(trackingNumber);
		assertEquals(order.getOrderID(), response.getOrderID());
	}
	
	@DisplayName("Find All Order By Order ID Desc")
	@Test
	public void testFindAllByOrderByOrderIDDesc() {
		Orders order = new Orders();
		order.setOrderID(122l);
		order.setAmount(150.00);
		order.setDateOrdered(new Date());
		order.setDaysIntransit(2);
		order.setProvince("Batangas");
		
		Orders order2 = new Orders();
		order2.setOrderID(105l);
		order2.setAmount(150.00);
		order2.setDateOrdered(new Date());
		order2.setDaysIntransit(2);
		order2.setProvince("Batangas");
		
		List<Orders> orderList = new ArrayList<>();
		orderList.add(order);
		orderList.add(order2);
		
		Collections.reverse(orderList);
		
		when(repository.findAllByOrderByOrderIDDesc()).thenReturn(orderList);
		
		List<ProductsDTO> response = service.findAllByOrderByOrderIDDesc();
		assertEquals(2, response.size());
		assertEquals(response.get(0).getOrderID(), 105l);
	}
	
	@DisplayName("Find Orders By Date Ordered")
	@Test
	public void testFindOrdersByDateOrdered(){
		Date orderDate = new Date();
		
		Orders order = new Orders();
		order.setOrderID(122l);
		order.setAmount(150.00);
		order.setDateOrdered(new Date());
		order.setDaysIntransit(2);
		order.setProvince("Batangas");
		
		Orders order2 = new Orders();
		order2.setOrderID(105l);
		order2.setAmount(150.00);
		order2.setDateOrdered(DateUtils.addDays(orderDate, 2));
		order2.setDaysIntransit(2);
		order2.setProvince("Batangas");
		
		DateUtils.addDays(orderDate, 2);
		
		List<Orders> orderList = new ArrayList<Orders>();
		orderList.add(order);
		orderList.add(order2);
		
		when(repository.findOrdersByDateOrdered(orderDate)).thenReturn(orderList);
		
		assertTrue(!service.findOrdersByDateOrdered(orderDate).isEmpty());
	}
	
	@Disabled
	@DisplayName("Save Order")
	@Test
	public void testSaveOrder() {
		Orders order = createOrder();
		ProductsDTO product = createProducts();
		
		when(repository.save(order)).thenReturn(new Orders());
		
		ProductsDTO response = service.saveOrder(product);
		OrderEntityMapper.convertProductToOrders(response);
		assertNotNull(response);
	}
	
	@Disabled
	@DisplayName("Update Order")
	@Test
	public void testUpdateOrder() {
		Orders order = createOrder();
		ProductsDTO product = createProducts();
		
		//when(repository.updateOrder(order)).thenReturn(new Orders());
		
		ProductsDTO response = service.updateOrder(product);
		OrderEntityMapper.convertProductToOrders(response);
		assertNotNull(response);
	}
	
	@DisplayName("Find Order By ID")
	@Test
	public void testFindById() {
		Long orderID = 122l;
		Optional<Orders> order = Optional.of(createOrder());
		
		when(repository.findById(orderID)).thenReturn(order);
		
		ProductsDTO response = service.findById(orderID);
		
		assertEquals(orderID, response.getOrderID());
	}
	
	private Orders createOrder() {
		Orders order = new Orders();
		order.setOrderID(122l);
		order.setAmount(150.00);
		order.setDateOrdered(new Date());
		order.setDaysIntransit(2);
		order.setProvince("Batangas");
		
		return order;
	}
	
	private ProductsDTO createProducts() {
		ProductsDTO product = new ProductsDTO();
		product.setOrderID(456l);
		product.setAmount(135.00);
		product.setDateOrdered(new Date());
		product.setDaysIntransit(4);
		product.setProvince("Rizal");
		
		return product;
	}
}
