package com.topyougo.productimport.dto;

public enum UserType {
	ADMIN("ADMIN"),
	WAREHOUSE("WAREHOUSE"),
	LOGISTICS("LOGISTICS"),
	FINANCE("FINANCE");
	
	String value;
	
	private UserType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}