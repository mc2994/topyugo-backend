package com.topyougo.productimport.dto;

import java.util.Map;

public class ShopifyLineItems {
	
	private String title;
	private Integer quantity;
	private String variant_title;
	private Double price;
	private PriceSet price_set;
	
	public ShopifyLineItems() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getVariant_title() {
		return variant_title;
	}

	public void setVariant_title(String variant_title) {
		this.variant_title = variant_title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PriceSet getPrice_set() {
		return price_set;
	}

	public void setPrice_set(PriceSet price_set) {
		this.price_set = price_set;
	}
}

class PriceSet{
	private Map<String, String> shop_money;
	private Map<String, String> presentment_money;
	
	public PriceSet() {
		
	}

	public Map<String, String> getShop_money() {
		return shop_money;
	}
	public void setShop_money(Map<String, String> shop_money) {
		this.shop_money = shop_money;
	}
	public Map<String, String> getPresentment_money() {
		return presentment_money;
	}
	public void setPresentment_money(Map<String, String> presentment_money) {
		this.presentment_money = presentment_money;
	}
}