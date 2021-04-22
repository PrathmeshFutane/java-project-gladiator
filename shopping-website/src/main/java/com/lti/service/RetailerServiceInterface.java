package com.lti.service;

import java.util.List;

import com.lti.entity.Product;
import com.lti.entity.Retailer;

public interface RetailerServiceInterface {
	
	public Retailer login(String email, String password);
	
	public int register(Retailer retailer);
	
	public List<Product> getProductsByRetailerId(int retailerId);
}
