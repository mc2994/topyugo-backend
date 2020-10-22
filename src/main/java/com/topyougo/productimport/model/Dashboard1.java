package com.topyougo.productimport.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dashboard1")
public class Dashboard1 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name="Month")
	private String month;
	
	@Column(name="SalesGoal")
	private String salesGoal;

	@Column(name="Sales")
	private String sales;
	
	@Column(name="SalesPercentage")
	private Double salesPercentage;
	
	@Column(name="ShippedGoal")
	private String shippedGoal;
	
	@Column(name="Shipped")
	private String shipped;
	
	@Column(name="ShippingPercentage")
	private Double shippingPercentage;
	
	@Column(name="DeliveredGoal")
	private String deliveredGoal;
	
	@Column(name="Delivered")
	private String delivered;
	
	@Column(name="DeliveredPercentage")
	private Double deliveredPercentage;
	
	@Column(name="PaidGoal")
	private String paidGoal;
	
	@Column(name="Paid")
	private Double paid;
	
	@Column(name="PaidPercentage")
	private Double paidPercentage;
	
	
	public Dashboard1() {
		
	}

	public Dashboard1(Long id, String month, String salesGoal, String sales, Double salesPercentage, String shippedGoal,
			String shipped, Double shippingPercentage, String deliveredGoal, String delivered,
			Double deliveredPercentage, String paidGoal, Double paid, Double paidPercentage) {
		super();
		this.id = id;
		this.month = month;
		this.salesGoal = salesGoal;
		this.sales = sales;
		this.salesPercentage = salesPercentage;
		this.shippedGoal = shippedGoal;
		this.shipped = shipped;
		this.shippingPercentage = shippingPercentage;
		this.deliveredGoal = deliveredGoal;
		this.delivered = delivered;
		this.deliveredPercentage = deliveredPercentage;
		this.paidGoal = paidGoal;
		this.paid = paid;
		this.paidPercentage = paidPercentage;
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

	@Override
	public String toString() {
		return "Dashboard1Repository [month=" + month + ", salesGoal=" + salesGoal + ", sales=" + sales + ", salesPercentage="
				+ salesPercentage + ", shippedGoal=" + shippedGoal + ", shipped=" + shipped + ", shippingPercentage="
				+ shippingPercentage + ", deliveredGoal=" + deliveredGoal + ", delivered=" + delivered
				+ ", deliveredPercentage=" + deliveredPercentage + ", paidGoal=" + paidGoal + ", paid=" + paid
				+ ", paidPercentage=" + paidPercentage + "]";
	}
}