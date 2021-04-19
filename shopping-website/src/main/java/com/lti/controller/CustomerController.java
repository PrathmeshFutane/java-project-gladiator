package com.lti.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.SendEmail;
import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.service.CustomerService;
import com.lti.service.CustomerServiceInterface;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerServiceInterface customerServiceInterface;	
	
	@PostMapping("/register")
	public RegisterStatus registerCustomer(@RequestBody Customer customer) {
		try {
			int id = customerServiceInterface.register(customer);			
			RegisterStatus status = new RegisterStatus();
			status.setStatus(true);
			status.setMessage("Registration successfull");
			status.setRegisteredCustomerId(id);
			
			//send email
			System.out.println("preparing to send message ...");
			String message = "Welcome to webrash";
			String subject = "Thank you for registration";
			String to = customer.getEmail();
			String from = "webrashlti@gmail.com";
			
			SendEmail send = new SendEmail();
			send.sendEmail(message,subject,to,from);
			
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
	public LoginStatus loginCustomer(@RequestBody Login login) {
		try {
			Customer customer = customerServiceInterface.login(login.getEmail(), login.getPassword());
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