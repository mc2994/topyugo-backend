package com.topyougo.productimport.constant;

public enum Status {
	ACTIVE("ACTIVE"),
	IN_ACTIVE("IN_ACTIVE");
	
	String value;
	
	private Status(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static Status getValueOf(String statusValue) {
		if(statusValue.equalsIgnoreCase(Status.ACTIVE.name())) {
			return Status.ACTIVE;
		}else if(statusValue.equalsIgnoreCase("IN_ACTIVE")) {
			return Status.IN_ACTIVE;
		}
		return null;
	}
}
