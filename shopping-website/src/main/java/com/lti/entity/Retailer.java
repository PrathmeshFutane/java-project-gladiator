package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shopping_retailer")
public class Retailer {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="shopping_retailer_seq")
	@SequenceGenerator(sequenceName = "retailer_sequence", allocationSize = 1, name ="shopping_retailer_seq")
	@Column(name = "retailer_id")
	private int retailerId;
	
	@Column(name = "owner_name")
	private String ownerName;
	private String company;
	private String email;
	private String password;
	
	@Column(name = "mobile_number")
	private long mobileNumber;
	private int revenue;
	
	@Column(name = "retailer_status")
	private char retailerStatus;
	
	@OneToMany(mappedBy = "retailer",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;
	
	public char getRetailerStatus() {
		return retailerStatus;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setRetailerStatus(char retailerStatus) {
		this.retailerStatus = retailerStatus;
	}
	public int getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
		
		
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
