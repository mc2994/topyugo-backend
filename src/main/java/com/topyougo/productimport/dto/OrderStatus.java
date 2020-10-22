package com.topyougo.productimport.dto;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public enum OrderStatus{
	RTS("RTS"),
	DELIVERED("DELIVERED"),
	SHIPPED("SHIPPED"),
	SCHEDULED("SCHEDULED"),
	CANCELLED("CANCELLED"),
	RRTS("RRTS"),
	RESHIPPED("RESHIPPED"),
	ON_HOLD("ON_HOLD"),
	PAID("PAID"),
	NO_STOCK("NO_STOCK"),
	NOT_MOVING("NOT_MOVING"),
	LOST_PARCEL("LOST_PARCEL"),
	NEW_ORDER("NEW_ORDER"),
	INVALID_ADDRESS("INVALID_ADDRESS"),
	INVALID_AMOUNT("INVALID_AMOUNT"),
	INVALID_NO("INVALID_NO"),
	ODZ("ODZ"),
	PAID_LOST("PAID_LOST"),
	VOIDED("");
	
	String value;
	private OrderStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static String getValueOf(String enumValue) {
		if(enumValue.equals("ON-HOLD")) {
			return OrderStatus.ON_HOLD.getValue();
		}
		else if(enumValue.equalsIgnoreCase(OrderStatus.valueOf(enumValue).toString())) {
			return OrderStatus.valueOf(enumValue).toString();
		}
		return "";
	}
	
	public static String fromString(String text) {
	   Map<String, String> mapValues = new HashedMap<String, String>();
	   mapValues.put("RTS", "RTS");
	   mapValues.put("DELIVERED", "DELIVERED");
	   mapValues.put("SHIPPED", "SHIPPED");
	   mapValues.put("SCHEDULED", "SCHEDULED");
	   mapValues.put("CANCELLED", "CANCELLED");
	   mapValues.put("RRTS", "RRTS");
	   mapValues.put("RESHIPPED", "RESHIPPED");
	   mapValues.put("ON_HOLD", "ON_HOLD");
	   mapValues.put("PAID", "PAID");
	   
	   if(mapValues.containsKey(text)) {
		   return mapValues.get(text);
	   }
	return text;
	}
}