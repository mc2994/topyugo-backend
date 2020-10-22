package com.topyougo.productimport.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dashboard2")
public class Dashboard2 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Month")
	private Integer month;
	
	@Column(name="Year")
	private Integer year;
	
	@Column(name="Amount")
	private String amount;
	
	@Column(name="TotalOrders")
	private String totalOrders;
	
	@Column(name="CaptureRate")
	private String captureRate;
	
	@Column(name="ADS")
	private String ads;
	
	@Column(name="AOV")
	private String aov;
	
	@Column(name="ACPP")
	private String acpp;
	
	@Column(name="ROAS")
	private String roas;

	public Dashboard2() {
		
	}

	public Dashboard2(Integer month, Integer year, String amount, String totalOrders, String captureRate, String ads,
			String aov, String acpp, String roas) {
		super();
		this.month = month;
		this.year = year;
		this.amount = amount;
		this.totalOrders = totalOrders;
		this.captureRate = captureRate;
		this.ads = ads;
		this.aov = aov;
		this.acpp = acpp;
		this.roas = roas;
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

	@Override
	public String toString() {
		return "Dashboard2 [month=" + month + ", year=" + year + ", amount=" + amount + ", totalOrders=" + totalOrders
				+ ", captureRate=" + captureRate + ", ads=" + ads + ", aov=" + aov + ", acpp=" + acpp + ", roas=" + roas
				+ "]";
	}
}