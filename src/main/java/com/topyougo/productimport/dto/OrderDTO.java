package com.topyougo.productimport.dto;

import java.util.List;

public class OrderDTO {

	private List<ShopifyOrderDTO> orders;
	
	public OrderDTO() {
		
	}

	public OrderDTO(List<ShopifyOrderDTO> orders) {
		super();
		this.orders = orders;
	}

	public List<ShopifyOrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<ShopifyOrderDTO> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrderDTO [orders=" + orders + ", getOrders()=" + getOrders() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
