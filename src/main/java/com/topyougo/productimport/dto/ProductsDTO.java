package com.topyougo.productimport.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.topyougo.productimport.constant.Courier;
import com.topyougo.productimport.constant.OrderStatus;
import com.topyougo.productimport.constant.TrackingStatus;

public class ProductsDTO {

	@NotEmpty(message = "This field is required")
	private Long orderID;
	
	@NotEmpty(message = "This field is required")  
	private String orderNo;
	private Date dateOrdered;
	private String firstName;
	private String lastName;
	private OrderStatus orderStatus;
	private Double codAmount;
	private String contactNo;
	private String product;
	private String variant;
	private Double amount;
	private Integer quantity;
	private Double orderAmount;
	private String address;
	private Date shippingDate;
	private Courier courier;
	private String customerNote;
	private String rtsReason;
	private String rtsDetails;
	private Date dateIntransit;
	private Integer daysIntransit;
	private Double shippingFee;
	private String province;
	private String region;
	private String trackingNumber;
	private TrackingStatus trackingStatus;

	public ProductsDTO() {

	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getCodAmount() {
		return codAmount;
	}

	public void setCodAmount(Double codAmount) {
		this.codAmount = codAmount;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public String getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}

	public String getRtsReason() {
		return rtsReason;
	}

	public void setRtsReason(String rtsReason) {
		this.rtsReason = rtsReason;
	}

	public String getRtsDetails() {
		return rtsDetails;
	}

	public void setRtsDetails(String rtsDetails) {
		this.rtsDetails = rtsDetails;
	}

	public Date getDateIntransit() {
		return dateIntransit;
	}

	public void setDateIntransit(Date dateIntransit) {
		this.dateIntransit = dateIntransit;
	}

	public Integer getDaysIntransit() {
		return daysIntransit;
	}

	public void setDaysIntransit(Integer daysIntransit) {
		this.daysIntransit = daysIntransit;
	}

	public Double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public TrackingStatus getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(TrackingStatus trackingStatus) {
		this.trackingStatus = trackingStatus;
	}
}