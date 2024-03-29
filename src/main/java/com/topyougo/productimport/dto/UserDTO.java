package com.topyougo.productimport.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.topyougo.productimport.model.Role;

public class UserDTO {

	@NotEmpty(message = "Username field is required")   
	private String username;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
	
	@Size(min = 8, message = "Password should be less than 8 characters")
	private String password;
	
	@NotEmpty(message = "This field is required")   
	private String firstName; 
	
	@NotEmpty(message = "This field is required")
	private String lastName;
	
	@NotEmpty(message = "This field is required")
	private String userType;
	
	@NotEmpty(message = "This field is required")
	private String status;
	
	private Set<Role> roles;
	
	public UserDTO() {
		
	}	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}