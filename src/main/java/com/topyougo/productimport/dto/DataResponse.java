package com.topyougo.productimport.dto;

import java.util.List;

public class DataResponse {
	
	private String billcode;
	private List<ResponseDetails> details;

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public List<ResponseDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ResponseDetails> details) {
		this.details = details;
	}
}