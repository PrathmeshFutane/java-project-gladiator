package com.lti.controller;

import java.util.Base64;

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
import com.lti.dto.Status;
import com.lti.entity.Cart;
import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.repository.CustomerRepository;
import com.lti.service.CustomerService;
import com.lti.service.CartServiceInterface;
import com.lti.service.CustomerServiceInterface;

@RestController
@CrossOrigin
public class CustomerController {
	
	int otp = 0;
	
	@Autowired
	private CustomerServiceInterface customerServiceInterface;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/register")
	public RegisterStatus registerCustomer(@RequestBody Customer customer) {
		try {
			customer.setAccountStatus('Y');
			int id = customerServiceInterface.register(customer);	
			
			RegisterStatus status = new RegisterStatus();
			status.setStatus(true);
			status.setMessage("Registration successfull");
			status.setRegisteredCustomerId(id);
			
//			Cart cart = new Cart()
//			int cid = CartServiceInterface.addCart(cart);			
			
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
			Customer c = customerRepository.fetch(Customer.class, customer.getCustomerId());
			loginStatus.setCartId(c.getCart().getCartId());
			return loginStatus;
		}
		catch(CustomerServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(false);
			loginStatus.setMessage(e.getMessage());		
			return loginStatus;
		}
	}
	
	
	
	//get profile of customer
	@GetMapping("/customer-profile")
	public Customer profile(@RequestParam("customerId") int id) {
		Customer customer = customerServiceInterface.getCustomerProfile(id);
		return customer;
	}
	
	
	@PostMapping("/update-customer")
		public Status updateCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerRepository.fetch(Customer.class, customer.getCustomerId());

		newCustomer.setName(customer.getName());
		newCustomer.setAddress1(customer.getAddress1());
		newCustomer.setAddress2(customer.getAddress2());
		

		customerServiceInterface.updateCustomer(newCustomer);
		Status status = new Status();
		status.setStatus(true);
		status.setMessage("Customer updated successfully!");
		return status;
	}
	
	
	@PostMapping("/send-otp")
	public RegisterStatus sendOtp(@RequestBody Customer customer) {
		RegisterStatus status = new RegisterStatus();
		try{
			System.out.println(customer.getEmail());
			System.out.println("-------"+customer.getCustomerId());
			int id = customerRepository.fetchByEmail(customer.getEmail());
			System.out.println("-------"+id);
			status.setRegisteredCustomerId(id);
			
			status.setMessage("Email id Exist");
			status.setStatus(true);
			otp = (int) Math.floor(Math.random()*1000000); 
			System.out.println("from send otp "+otp);
			
			//code to send email
			String message = "OTP is : "+otp;
			String subject = "change password";
			String to = customer.getEmail();
			String from = "webrashlti@gmail.com";
			
			SendEmail sendEmail = new SendEmail();
			sendEmail.sendEmail(message,subject,to,from);
			
			return status;
		}
		catch(Exception e) {
			status.setMessage("email not found");
			status.setStatus(false);
			return status;
		}
			
		
	}
	
	@GetMapping("/check-otp")
	public Status checkOtp(@RequestParam("otp") int otp) {
		Status status = new Status();
		System.out.println("from check otp frontend "+otp);
		System.out.println("from send "+this.otp);
		if(this.otp==otp) {
			status.setMessage("otp match");
			status.setStatus(true);
			System.out.println("match");
			return status;
		}
		else {
			status.setMessage("mismatch otp");
			System.out.println("not match");
			return status;
		}
	}
	
	
	@PostMapping("/update-password")
	public Status updatePassword(@RequestBody Customer customer) {
		Status status = new Status();
		System.out.println(customer.getPassword()+"   "+customer.getCustomerId());
		Customer existingCustomer = customerRepository.fetch(Customer.class, customer.getCustomerId());
		existingCustomer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
		customerServiceInterface.updateCustomer(existingCustomer);
		status.setMessage("customer Updated successsfully");
		status.setStatus(true);
		return status;
	}
	
	
	
	
	
}