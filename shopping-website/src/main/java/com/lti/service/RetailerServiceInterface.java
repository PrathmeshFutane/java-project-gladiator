package com.lti.service;

import com.lti.entity.Retailer;

public interface RetailerServiceInterface {
	
	public Retailer login(String email, String password);
	
	public int register(Retailer retailer);
}
