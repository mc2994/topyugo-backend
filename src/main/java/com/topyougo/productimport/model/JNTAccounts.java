package com.topyougo.productimport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jntaccounts")
public class JNTAccounts {

	@Id
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="UserName")
	private String userName;
	
	@Column(name="Password")
	private String password;
	
	public JNTAccounts() {
		
	}

	
	
	public JNTAccounts(Integer userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JNTAccounts [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
}
