package com.topyougo.productimport.dto;

public class Dashboard2DTO {

	private Integer month;	
	private Integer year;
	private String amount;	
	private String totalOrders;	
	private String captureRate;	
	private String ads;	
	private String aov;	
	private String acpp;	
	private String roas;

	public Dashboard2DTO() {
		
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(String totalOrders) {
		this.totalOrders = totalOrders;
	}

	public String getCaptureRate() {
		return captureRate;
	}

	public void setCaptureRate(String captureRate) {
		this.captureRate = captureRate;
	}

	public String getAds() {
		return ads;
	}

	public void setAds(String ads) {
		this.ads = ads;
	}

	public String getAov() {
		return aov;
	}

	public void setAov(String aov) {
		this.aov = aov;
	}

	public String getAcpp() {
		return acpp;
	}

	public void setAcpp(String acpp) {
		this.acpp = acpp;
	}

	public String getRoas() {
		return roas;
	}

	public void setRoas(String roas) {
		this.roas = roas;
	}
}