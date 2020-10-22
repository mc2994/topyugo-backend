package com.topyougo.productimport.dto;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {
	
	private String billcode;
	private List<ResponseDetails> details;

	public DataResponse() {
		this.billcode = "";
		this.details = new ArrayList<ResponseDetails>();
	}

	public DataResponse(String billcode, List<ResponseDetails> details) {
		super();
		this.billcode = billcode;
		this.details = details;
	}

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

	@Override
	public String toString() {
		return "DataResponse [billcode=" + billcode + ", details=" + details + "]";
	}
}