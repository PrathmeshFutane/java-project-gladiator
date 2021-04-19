package com.lti.service;



import com.lti.entity.Customer;

public interface CustomerServiceInterface {
	public int register(Customer customer);
		
	
	public Customer login(String email, String password);
	
	
	public Customer getCustomerProfile(int id);
	
}
