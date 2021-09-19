package com.topyougo.productimport.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.topyougo.productimport.constant.Courier;
import com.topyougo.productimport.model.Orders;


class OrderRepositoryIT extends BaseRepositoryTestConfig {
	
	@Autowired
	private OrderRepository orderRepository;

	@Test
	void testFindOrdersByOrderNoAndProduct() {
		Orders order = createOrder();
		
		orderRepository.save(order);
		
		Orders response = orderRepository.findOrdersByOrderNoAndProduct(order.getOrderNo(), order.getProduct());
		
		assertEquals(response.getOrderNo(), order.getOrderNo());
		assertEquals(response.getProduct(), order.getProduct());
	}

	@Test
	void testFindOrdersByOrderNo() {
		Orders order = createOrder();
		
		orderRepository.save(order);
		
		Orders response = orderRepository.findOrdersByOrderNo(order.getOrderNo());
		
		assertThat(response).isNotNull();
		assertEquals(response.getOrderNo(), order.getOrderNo());
	}

	@Test
	void testFindOrdersByTrackingNumber() {
		Orders order = createOrder();
		
		orderRepository.save(order);
		
		Orders response = orderRepository.findOrdersByTrackingNumber(order.getTrackingNumber());
		
		assertThat(response).isNotNull();
		assertEquals(response.getTrackingNumber(), order.getTrackingNumber());
	}

	@Test
	void testFindAllByOrderByOrderIDDesc() {
		Orders order = createOrder();
		
		orderRepository.save(order);
		
		List<Orders> response = orderRepository.findAllByOrderByOrderIDDesc();
		
		assertThat(response).isNotNull();
		assertEquals(response.get(0).getTrackingNumber(), order.getTrackingNumber());
	}

	@Test
	void testFindOrdersByDateOrdered() {
		Orders order = createOrder();
		
		orderRepository.save(order);
		
		List<Orders> response = orderRepository.findOrdersByDateOrdered(order.getDateOrdered());
		
		assertThat(response).isNotNull();
		assertEquals(response.get(0).getDateOrdered(), order.getDateOrdered());
	}

	@Test
	void testFindAllByCourierOrderByIDDesc() {
		Orders order = createOrder();
		
		orderRepository.save(order);
		
		List<Orders> response = orderRepository.findAllByCourierOrderByIDDesc(order.getCourier().getValue());
		
		assertThat(response).isNotNull();
		assertEquals(response.get(0).getCourier().getValue(), Courier.JNT.getValue());
	}
	
	private Orders createOrder() {
		Orders order = new Orders();
		order.setOrderID(122l);
		order.setAmount(150.00);
		order.setOrderNo("WHST-002");
		order.setProduct("TCL TV");
		order.setTrackingNumber("WHL-36214");
		order.setDateOrdered(new Date());
		order.setDaysIntransit(2);
		order.setCourier(Courier.JNT);
		order.setProvince("Batangas");
		
		return order;
	}

}
