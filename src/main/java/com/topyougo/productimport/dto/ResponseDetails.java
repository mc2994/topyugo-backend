package com.topyougo.productimport.dto;

public class ResponseDetails {
	
	private String scantype;
	private String scanscode;
	private String scanstatus;
	private String desc;
	
	public ResponseDetails() {
		this.scantype = "";
		this.scanscode = "";
		this.scanstatus = "";
		this.desc = "";
	}

	public String getScantype() {
		return scantype;
	}

	public void setScantype(String scantype) {
		this.scantype = scantype;
	}

	public String getScanscode() {
		return scanscode;
	}

	public void setScanscode(String scanscode) {
		this.scanscode = scanscode;
	}

	public String getScanstatus() {
		return scanstatus;
	}

	public void setScanstatus(String scanstatus) {
		this.scanstatus = scanstatus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}