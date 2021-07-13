package com.topyougo.productimport.service;

import com.topyougo.productimport.service.impl.CSVReport;
import com.topyougo.productimport.service.impl.ExcelReport;

public class FileReportFactory {

	public Report reportType(String type) {
		Report report = null;
		switch(type) {
		case "xlsx":
			report =  new ExcelReport();
			break;
		case "csv":
			report =  new CSVReport();
			break;
		default:
			break;
		}
		return report;
		
	}
}
