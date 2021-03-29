package com.topyougo.productimport.config;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.topyougo.productimport.dto.Courier;
import com.topyougo.productimport.dto.DataResponse;
import com.topyougo.productimport.dto.OrderStatus;
import com.topyougo.productimport.dto.ResponseDetails;
import com.topyougo.productimport.dto.TrackingStatus;
import com.topyougo.productimport.model.Orders;
import com.topyougo.productimport.repository.OrderRepository;
import com.topyougo.productimport.service.JntIntegrationService;

@Component
public class JNTScheduler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private JntIntegrationService jntService;

	@Scheduled(fixedRate = 20000)
	public void scheduleFixedDelayTask() {
		List<Orders> orders = orderRepository.findAllByCourierOrderByIDDesc(Courier.JNT.getValue());
		logger.info("Get orders count ",orders.size());

		List<DataResponse> jntListOrders = new ArrayList<DataResponse>();
		orders.forEach(order -> {
			logger.info("Get tracking number ",order.getTrackingNumber());
			DataResponse jntResponse = jntService.fetchTrackingInfo(order.getTrackingNumber());
			jntListOrders.add(jntResponse);
		});

		for (DataResponse jnt : jntListOrders) {
			if (!jnt.getDetails().isEmpty()) {
				for (ResponseDetails res : jnt.getDetails()) {
					
					Orders order = orderRepository.findOrdersByTrackingNumber(jnt.getBillcode());
					
					logger.debug("Get billcode and ScanStatus",jnt.getBillcode() +" "+res.getScanstatus());
					
					if (res.getScanstatus().equals("Returned")) {
						order.setTrackingStatus(TrackingStatus.RETURN_TO_SENDER);
						order.setOrderStatus(OrderStatus.RTS);
					} else if (res.getScanstatus().equals("On Return")) {
						order.setTrackingStatus(TrackingStatus.RRTS);
						order.setOrderStatus(OrderStatus.RRTS);
					} else if (res.getScanstatus().equals("Delivered")) {
						order.setTrackingStatus(TrackingStatus.DELIVERED);
						order.setOrderStatus(OrderStatus.DELIVERED);
					} else {
						order.setTrackingStatus(TrackingStatus.SHIPPED);
						order.setOrderStatus(OrderStatus.SHIPPED);
					}
					orderRepository.save(order);
				}
			}
		}
	}
}