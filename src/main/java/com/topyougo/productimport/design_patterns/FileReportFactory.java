package com.topyougo.productimport.design_patterns;

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
