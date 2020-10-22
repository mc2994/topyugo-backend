package com.topyougo.productimport.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ShopifyLineItems implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private Integer quantity;
	private String variant_title;
	private Double price;
	private PriceSet price_set;
	
	public ShopifyLineItems() {
		
	}

	public ShopifyLineItems(String title, Integer quantity, String variant_title, Double price, PriceSet price_set) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.variant_title = variant_title;
		this.price = price;
		this.price_set = price_set;
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

	@Override
	public String toString() {
		return "ShopifyLineItems [title=" + title + ", quantity=" + quantity + ", variant_title=" + variant_title
				+ ", price=" + price + ", price_set=" + price_set + "]";
	}
}

class PriceSet{
	private Map<String, String> shop_money;
	private Map<String, String> presentment_money;
	
	public PriceSet() {
		
	}
	
	public PriceSet(Map<String, String> shop_money, Map<String, String> presentment_money) {
		this.shop_money = shop_money;
		this.presentment_money = presentment_money;
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

	@Override
	public String toString() {
		return "PriceSet [shop_money=" + shop_money + ", presentment_money=" + presentment_money + "]";
	}
}