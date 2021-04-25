package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CartItemStatus;
import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RetailerRegisterStatus;
import com.lti.entity.CartItem;
import com.lti.entity.Customer;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

import com.lti.exception.RetailerServiceException;
import com.lti.repository.RetailerRepository;
import com.lti.service.RetailerServiceInterface;

@RestController
@CrossOrigin
public class RetailerController {
	
	@Autowired
	private RetailerServiceInterface retailerServiceInterface;
	
	@PostMapping("/register-retailer")
	public RetailerRegisterStatus registerRetailer(@RequestBody Retailer retailer) {
		try {
			//int otp = customer.getOtp();
			//int genOtp = session.getAttribute("otp");
			retailer.setRetailerStatus('N');
			int id = retailerServiceInterface.register(retailer);
			RetailerRegisterStatus status = new RetailerRegisterStatus();
			status.setStatus(true);
			status.setMessage("Registration successful!");
			status.setRegisteredRetailerId(id);
			return status;
		}
		catch(RetailerServiceException e) {
			RetailerRegisterStatus status = new RetailerRegisterStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/login-retailer")
	public LoginStatus loginRetailer(@RequestBody Login login) {
		try {
			Retailer retailer = retailerServiceInterface.login(login.getEmail(), login.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(true);
			loginStatus.setMessage("Login successful!");
			loginStatus.setId(retailer.getRetailerId());
			loginStatus.setName(retailer.getOwnerName());
			loginStatus.setApprovalStatus(retailer.getRetailerStatus());
			return loginStatus;
		}
		catch(RetailerServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(false);
			loginStatus.setMessage(e.getMessage());		
			return loginStatus;
		}
	}
	
	@GetMapping("/get-products-by-retailer")
	public List<Product> getAllProductByRetailerId(@RequestParam("retailerId") int retailerId){
		List<Product> products =  retailerServiceInterface.getProductsByRetailerId(retailerId);
		return products;
		
	}
	
	
	@GetMapping("/retailer-profile")
	public Retailer profile(@RequestParam("retailerId") int id) {
		Retailer retailer = retailerServiceInterface.getRetailerProfile(id);
		return retailer;
	}
	
	
}
