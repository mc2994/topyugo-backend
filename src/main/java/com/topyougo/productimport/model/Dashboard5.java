package com.topyougo.productimport.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="dashboard5")
//@NamedStoredProcedureQuery(
//    name = "dashboard5.dashboard5", 
//    procedureName = "DASHBOARD5", 
//    parameters = { 
//    	@StoredProcedureParameter(
//    		mode = ParameterMode.IN, 
//    		name = "monthOrder", 
//    		type = String.class),
//    	@StoredProcedureParameter(
//    		mode = ParameterMode.IN, 
//    		name = "yearOrder", 
//    		type = String.class)
//    }) 
public class Dashboard5 implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Rank")
	private Integer rank;
	
	@Column(name="Product")
	private String product;
	
	@Column(name="Sales")
	private String sales;
		
	@Column(name="SalesRate")
	private Double salesRate;
	
	public Dashboard5() {
		
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public Double getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(Double salesRate) {
		this.salesRate = salesRate;
	}
}