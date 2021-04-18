package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RegisterStatus;
import com.lti.entity.Cart;
import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.service.CartService;
import com.lti.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
//	@Autowired
//	private CartService cartService;
	
	@Autowired
	private CartController cartController;
	
	
	@PostMapping("/register")
	public RegisterStatus register(@RequestBody Customer customer) {
		try {
			int id = customerService.register(customer);
//			Cart cart = new Cart();
//			cart.setCustomer(customer);
//			int cid = cartService.register(cart);
//			Cart cart = new Cart();
//			cart.setCustomer(customer);
//			cartController.addToCart(cart);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(true);
			status.setMessage("Registration successfull");
			status.setRegisteredCustomerId(id);
			return status;
		}
		catch(CustomerServiceException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	@PostMapping("/login")
	public LoginStatus login(@RequestBody Login login) {
		try {
			Customer customer = customerService.login(login.getEmail(), login.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(true);
			loginStatus.setMessage("Login successful!");
			loginStatus.setId(customer.getCustomerId());
			loginStatus.setName(customer.getName());
			return loginStatus;
		}
		catch(CustomerServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(false);
			loginStatus.setMessage(e.getMessage());		
			return loginStatus;
		}
	}
}