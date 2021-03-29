package com.topyougo.productimport.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UtilityClass {

	private UtilityClass() {
		
	}
	
	public static String toJson(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(obj);
	}
	
	public static Date formatDate(Date processedAt) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat dt = new SimpleDateFormat("MMM dd, yyyy");
		String date = null;
		Date finalDate = null;
		if (processedAt != null) {
			try {
				date = dt.format(processedAt);
				finalDate = dt.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return finalDate;
	}
	
	public static String fortmatDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
}
