package com.lti.service;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.repository.CustomerRepository;

//@Component
@Service
@Transactional  //for dml operations
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public int register(Customer customer)  {
		if(customerRepository.isCustomerPresent(customer.getEmail())) {
			throw new CustomerServiceException("Customer already register");
		}
		else {
			customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
			Customer updatedCustomer = (Customer) customerRepository.save(customer);
			//code to send email
			return updatedCustomer.getCustomerId();
		}
	}
}
