package com.topyougo.productimport.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShopifyOrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer number;
	private Long id;
	private Double total_price;
	private String financial_status;
	private String name;
	private Date processed_at;
	private Date created_at;
	private List<ShopifyLineItems> line_items;
	private CustomerInfo customer;
	
	
	public ShopifyOrderDTO() {
		
	}

	public ShopifyOrderDTO(Integer number, Long id, Double total_price, String financial_status, String name,
			Date processed_at, Date created_at, List<ShopifyLineItems> line_items, CustomerInfo customer) {
		super();
		this.number = number;
		this.id = id;
		this.total_price = total_price;
		this.financial_status = financial_status;
		this.name = name;
		this.processed_at = processed_at;
		this.created_at = created_at;
		this.line_items = line_items;
		this.customer = customer;
	}

	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getTotal_price() {
		return total_price;
	}


	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}


	public String getFinancial_status() {
		return financial_status;
	}


	public void setFinancial_status(String financial_status) {
		this.financial_status = financial_status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getProcessed_at() {
		return processed_at;
	}

	public void setProcessed_at(Date processed_at) {
		this.processed_at = processed_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public List<ShopifyLineItems> getLine_items() {
		return line_items;
	}


	public void setLine_items(List<ShopifyLineItems> line_items) {
		this.line_items = line_items;
	}


	public CustomerInfo getCustomer() {
		return customer;
	}


	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ShopifyOrderDTO [number=" + number + ", id=" + id + ", total_price=" + total_price
				+ ", financial_status=" + financial_status + ", name=" + name + ", processed_at=" + processed_at
				+ ", created_at=" + created_at + ", line_items=" + line_items + ", customer=" + customer + "]";
	}
}