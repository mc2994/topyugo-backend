package com.topyougo.productimport.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	public static Date formatDate(Date processedAt) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
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
	
	public static Date convertStringToDate(String dateInput) {
		DateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		try {
			if (!StringUtils.isBlank((dateInput))) {
				Date date = format.parse(dateInput);
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
