package com.topyougo.productimport.constant;

public enum TrackingStatus {
	DELIVERED("DELIVERED"),
	SHIPPED("SHIPPED"),
	RETURN_TO_SENDER("RETURN_TO_SENDER"),
	RRTS("RRTS"),
	NO_STATUS("NO_STATUS");
	
	String value;
	
	private TrackingStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
