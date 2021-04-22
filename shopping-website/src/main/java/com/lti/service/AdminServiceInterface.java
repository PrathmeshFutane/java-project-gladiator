package com.lti.service;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Retailer;

public interface AdminServiceInterface {

	public int register(Admin admin);

	public Admin login(String email, String password);
	
	public Admin getAdminProfile(int id);

	public List<Customer> getTotalCustomer();
	
	public List<Retailer> getTotalRetailer();
}
