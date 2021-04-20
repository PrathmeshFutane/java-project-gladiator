package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_admin")
public class Admin {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="shopping_admin_seq")
	@SequenceGenerator(sequenceName = "admin_sequence", allocationSize = 1, name ="shopping_admin_seq")
	@Column(name = "admin_id")
	private int adminId;
	private String name;
	private String email;
	private String password;
	
	@Column(name = "mobile_number")
	private long mobileNumber;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	
}
