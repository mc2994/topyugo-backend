package com.topyougo.productimport.dto;

import java.util.Map;

public class ObjectResponse {
	
	private Map<String, String> sqlid;
	private String code;
	private DataResponse data;
	private boolean success;
	
	public ObjectResponse() {
		
	}

	public Map<String, String> getSqlid() {
		return sqlid;
	}
	public void setSqlid(Map<String, String> sqlid) {
		this.sqlid = sqlid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public DataResponse getData() {
		return data;
	}
	public void setData(DataResponse data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}