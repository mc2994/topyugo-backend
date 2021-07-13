package com.topyougo.productimport.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.topyougo.productimport.constant.Courier;
import com.topyougo.productimport.constant.OrderStatus;
import com.topyougo.productimport.constant.TrackingStatus;

@Entity
@Table(name="orders")
public class Orders implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long orderID;
	
	@Column(name="DateOrdered")
	@Temporal(TemporalType.DATE)
	private Date dateOrdered;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="OrderNo")
	private String orderNo;
	
	@Column(name="OrderStatus", length = 45)
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@Column(name="CodAmount")
	private Double codAmount;
	
	@Column(name="ContactNo")
	private String contactNo;
	
	@Column(name="Product")
	private String product;
	
	@Column(name="Variant")
	private String variant;
	
	@Column(name="Amount")
	private Double amount;
	
	@Column(name="Quantity")
	private Integer quantity;
	
	@Column(name="OrderAmount")
	private Double orderAmount;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="ShippingDate")
	private Date shippingDate;
	
	@Column(name="Courier", length = 45)
	@Enumerated(EnumType.STRING)
	private Courier courier;
	
	@Column(name="CustomerNote")
	private String customerNote;
	
	@Column(name="ShippingDate2")
	private Date shippingDate2;
	
	@Column(name="RTSReason")
	private String rtsReason;
	
	@Column(name="RTSDetails")
	private String rtsDetails;
	
	@Column(name="DateInTransit")
	private Date dateIntransit;
	
	@Column(name="DaysIntransit")
	private Integer daysIntransit;
	
	@Column(name="ShippingFee")
	private Double shippingFee;
	
	@Column(name="Province")
	private String province;
	
	@Column(name="Region")
	private String region;
	
	@Column(name="TrackingNumber")
	private String trackingNumber;
	
	@Column(name="TrackingStatus", length = 45)
	@Enumerated(EnumType.STRING)
	private TrackingStatus trackingStatus;
	
	public Orders() {
		
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Date getShippingDate2() {
		return shippingDate2;
	}

	public void setShippingDate2(Date shippingDate2) {
		this.shippingDate2 = shippingDate2;
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