package com.topyougo.productimport.constant;

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