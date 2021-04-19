package com.lti.service;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.repository.CustomerRepository;

//@Component
@Service
@Transactional  //for dml operations
public class CustomerService  implements CustomerServiceInterface {
	
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
			System.out.println("this is for testing");
			System.out.println("this is proper testing");
			return updatedCustomer.getCustomerId();
			
		}
	}
	
	public Customer login(String email, String password) {
		try {
			password = Base64.getEncoder().encodeToString(password.getBytes());
			int id = customerRepository.fetchIdByEmailAndPassword(email, password);
			Customer customer = customerRepository.fetch(Customer.class, id);
			return customer;
		}
		catch(EmptyResultDataAccessException e) {
		//catch(NoResultException e) {
			throw new CustomerServiceException("Invalid email/password");
		}
	}
	
	public Customer getCustomerProfile(int id) {
		return customerRepository.fetch(Customer.class, id);
	}
	
	
}
