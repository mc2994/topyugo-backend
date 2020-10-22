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

	public ResponseDetails(String scantype, String scanscode, String scanstatus, String desc) {
		super();
		this.scantype = scantype;
		this.scanscode = scanscode;
		this.scanstatus = scanstatus;
		this.desc = desc;
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

	@Override
	public String toString() {
		return "ResponseDetails [scantype=" + scantype + ", scanscode=" + scanscode + ", scanstatus=" + scanstatus
				+ ", desc=" + desc + "]";
	}
}
