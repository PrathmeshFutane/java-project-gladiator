package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RetailerRegisterStatus;
import com.lti.entity.Retailer;

import com.lti.exception.RetailerServiceException;
import com.lti.service.RetailerService;

@RestController
@CrossOrigin
public class RetailerController {
	
	@Autowired
	private RetailerService retailerService;

	
	@PostMapping("/retailer")
	public RetailerRegisterStatus register(@RequestBody Retailer retailer) {
		try {
			//int otp = customer.getOtp();
			//int genOtp = session.getAttribute("otp");
			
			int id = retailerService.register(retailer);
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
	
	@PostMapping("/retailerlogin")
	public LoginStatus login(@RequestBody Login login) {
		try {
			Retailer retailer = retailerService.login(login.getEmail(), login.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(true);
			loginStatus.setMessage("Login successful!");
			loginStatus.setId(retailer.getRetailerId());
			loginStatus.setName(retailer.getOwnerName());
			return loginStatus;
		}
		catch(RetailerServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(false);
			loginStatus.setMessage(e.getMessage());		
			return loginStatus;
		}
	}
}
