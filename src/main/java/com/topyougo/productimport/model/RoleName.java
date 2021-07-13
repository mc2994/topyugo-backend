package com.topyougo.productimport.model;

public enum RoleName {
	ADMIN("ADMIN"),	WAREHOUSE("WAREHOUSE"),	LOGISTICS("LOGISTICS"),	FINANCE("FINANCE");
	
	String value;
	private RoleName(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
