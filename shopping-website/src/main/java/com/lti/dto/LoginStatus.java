package com.lti.dto;

public class LoginStatus extends Status {

	private int id;
	private String name;
	//private Customer customer;
	
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}