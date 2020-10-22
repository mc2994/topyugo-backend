package com.topyougo.productimport.dto;

public class DefaultAddress{
	
	private String province;
	private String address1;
	
	public DefaultAddress() {
		
	}
	
	public DefaultAddress(String province, String address1) {
		super();
		this.province = province;
		this.address1 = address1;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Override
	public String toString() {
		return "DefaultAddress [province=" + province + ", address1=" + address1 + "]";
	}
}
