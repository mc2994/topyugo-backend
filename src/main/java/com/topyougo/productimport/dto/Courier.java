package com.topyougo.productimport.dto;

public enum Courier {
	JNT("JNT"),
	LBC("LBC"),
	JRS("JRS"),
	NO_COURIER("");
	
	 private String value;
	
	private Courier(String value) {
		this.value = value;		
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static Courier getValueOf(String enumValue) {
		if(enumValue.equalsIgnoreCase(Courier.valueOf(enumValue).getValue())) {
			return Courier.valueOf(enumValue);
		}
		return null;
	}
	
	public static Courier checkValueOf(String courierValue) {
		if (courierValue.equals(Courier.JNT.getValue())) {
			return Courier.JNT;
		}
		if (courierValue.equals(Courier.LBC.getValue())) {
			return Courier.LBC;
		}
		return Courier.JRS;
	}
}
