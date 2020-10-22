package com.topyougo.productimport.dto;

import java.util.Date;

public class ProductsDTO {

	private Long orderID;
	private String orderNo;
	private Date dateOrdered;
	private String firstName;
	private String lastName;
	private String orderStatus;
	private Double codAmount;
	private String contactNo;
	private String product;
	private String variant;
	private Double amount;
	private Integer quantity;
	private Double orderAmount;
	private String address;
	private Date shippingDate;
	private String courier;
	private String customerNote;
	private String rtsReason;
	private String rtsDetails;
	private Date dateIntransit;
	private Integer daysIntransit;
	private Double shippingFee;
	private String province;
	private String region;
	private String trackingNumber;
	private String trackingStatus;

	public ProductsDTO() {

	}

	public ProductsDTO(Long orderID, String orderNo, Date dateOrdered, String firstName, String lastName,
			String orderStatus, Double codAmount, String contactNo, String product, String variant, Double amount,
			Integer quantity, Double orderAmount, String address, Date shippingDate, String courier,
			String customerNote, String rtsReason, String rtsDetails, Date dateIntransit, Integer daysIntransit,
			Double shippingFee, String province, String region, String trackingNumber, String trackingStatus) {
		super();
		this.orderID = orderID;
		this.orderNo = orderNo;
		this.dateOrdered = dateOrdered;
		this.firstName = firstName;
		this.lastName = lastName;
		this.orderStatus = orderStatus;
		this.codAmount = codAmount;
		this.contactNo = contactNo;
		this.product = product;
		this.variant = variant;
		this.amount = amount;
		this.quantity = quantity;
		this.orderAmount = orderAmount;
		this.address = address;
		this.shippingDate = shippingDate;
		this.courier = courier;
		this.customerNote = customerNote;
		this.rtsReason = rtsReason;
		this.rtsDetails = rtsDetails;
		this.dateIntransit = dateIntransit;
		this.daysIntransit = daysIntransit;
		this.shippingFee = shippingFee;
		this.province = province;
		this.region = region;
		this.trackingNumber = trackingNumber;
		this.trackingStatus = trackingStatus;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
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

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
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

	public String getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}

	@Override
	public String toString() {
		return "ProductsDTO [orderID=" + orderID + ", orderNo=" + orderNo + ", dateOrdered=" + dateOrdered
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", orderStatus=" + orderStatus
				+ ", codAmount=" + codAmount + ", contactNo=" + contactNo + ", product=" + product + ", variant="
				+ variant + ", amount=" + amount + ", quantity=" + quantity + ", orderAmount=" + orderAmount
				+ ", address=" + address + ", shippingDate=" + shippingDate + ", courier=" + courier + ", customerNote="
				+ customerNote + ", rtsReason=" + rtsReason + ", rtsDetails=" + rtsDetails + ", dateIntransit="
				+ dateIntransit + ", daysIntransit=" + daysIntransit + ", shippingFee=" + shippingFee + ", province="
				+ province + ", region=" + region + ", trackingNumber=" + trackingNumber + ", trackingStatus="
				+ trackingStatus + "]";
	}
}