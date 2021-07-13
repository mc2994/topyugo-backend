package com.topyougo.productimport.dto;

public class Dashboard1DTO {

	private Long id;	
	private String month;	
	private String salesGoal;
	private String sales;	
	private Double salesPercentage;	
	private String shippedGoal;	
	private String shipped;	
	private Double shippingPercentage;	
	private String deliveredGoal;	
	private String delivered;	
	private Double deliveredPercentage;	
	private String paidGoal;	
	private Double paid;	
	private Double paidPercentage;	
	private String currentDate;
	
	public Dashboard1DTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSalesGoal() {
		return salesGoal;
	}

	public void setSalesGoal(String salesGoal) {
		this.salesGoal = salesGoal;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public Double getSalesPercentage() {
		return salesPercentage;
	}

	public void setSalesPercentage(Double salesPercentage) {
		this.salesPercentage = salesPercentage;
	}

	public String getShippedGoal() {
		return shippedGoal;
	}

	public void setShippedGoal(String shippedGoal) {
		this.shippedGoal = shippedGoal;
	}

	public String getShipped() {
		return shipped;
	}

	public void setShipped(String shipped) {
		this.shipped = shipped;
	}

	public Double getShippingPercentage() {
		return shippingPercentage;
	}

	public void setShippingPercentage(Double shippingPercentage) {
		this.shippingPercentage = shippingPercentage;
	}

	public String getDeliveredGoal() {
		return deliveredGoal;
	}

	public void setDeliveredGoal(String deliveredGoal) {
		this.deliveredGoal = deliveredGoal;
	}

	public String getDelivered() {
		return delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	public Double getDeliveredPercentage() {
		return deliveredPercentage;
	}

	public void setDeliveredPercentage(Double deliveredPercentage) {
		this.deliveredPercentage = deliveredPercentage;
	}

	public String getPaidGoal() {
		return paidGoal;
	}

	public void setPaidGoal(String paidGoal) {
		this.paidGoal = paidGoal;
	}

	public Double getPaid() {
		return paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Double getPaidPercentage() {
		return paidPercentage;
	}

	public void setPaidPercentage(Double paidPercentage) {
		this.paidPercentage = paidPercentage;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
}