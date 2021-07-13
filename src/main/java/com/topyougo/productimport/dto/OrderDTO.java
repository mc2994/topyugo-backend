package com.topyougo.productimport.dto;

import java.util.List;

public class OrderDTO {

	private List<ShopifyOrderDTO> orders;
	
	public OrderDTO() {
		
	}

	public List<ShopifyOrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<ShopifyOrderDTO> orders) {
		this.orders = orders;
	}
}