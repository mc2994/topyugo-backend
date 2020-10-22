package com.topyougo.productimport.dto;

import java.util.List;

public class CustomerInfo{
	private String first_name;
	private String last_name;
	private DefaultAddress default_address;
	private String phone;
	private String city;
	private String zip;
	
	public CustomerInfo() {
		
	}
	
	public CustomerInfo(String first_name, String last_name, DefaultAddress default_address, String phone,
			String city, String zip) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.default_address = default_address;
		this.phone = phone;
		this.city = city;
		this.zip = zip;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public DefaultAddress getDefault_address() {
		return default_address;
	}

	public void setDefault_address(DefaultAddress default_address) {
		this.default_address = default_address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "CustomerInfo [first_name=" + first_name + ", last_name=" + last_name + ", default_address="
				+ default_address + ", phone=" + phone + ", city=" + city + ", zip=" + zip + "]";
	}	
}