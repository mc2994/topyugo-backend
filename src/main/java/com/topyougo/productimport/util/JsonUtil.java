package com.topyougo.productimport.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private JsonUtil() {
		
	}
	
	public static String toJson(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(obj);
	}
}