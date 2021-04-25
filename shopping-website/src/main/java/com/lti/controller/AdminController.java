package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminStatus;
import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RetailerRegisterStatus;
import com.lti.dto.SendEmail;
import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Retailer;
import com.lti.exception.AdminServiceException;
import com.lti.repository.RetailerRepository;
import com.lti.service.AdminServiceInterface;
import com.lti.service.RetailerServiceInterface;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminServiceInterface adminServiceInterface;
	
	@Autowired
	private RetailerRepository retailerRepository;
	
	@PostMapping("/admin-register")
	public AdminStatus registerAdmin(@RequestBody Admin admin) {
		try {
			 int id = adminServiceInterface.register(admin);
			 AdminStatus status = new AdminStatus();
			 status.setStatus(true);
			 status.setMessage("Registration successfull");
			 status.setRegisteredAdminId(id);
			 return status;
		}
		catch(AdminServiceException e) {
			AdminStatus status = new AdminStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin-login")
	public LoginStatus loginAdmin(@RequestBody Login login) {
		try {
			Admin admin = adminServiceInterface.login(login.getEmail(),login.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(true);
			loginStatus.setMessage("Login succesfull");
			loginStatus.setId(admin.getAdminId());
			loginStatus.setName(admin.getName());
			return loginStatus;
		}
		catch(AdminServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(false);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}	
	}
	
	@GetMapping("/admin-profile")
	public Admin profile(@RequestParam("adminId") int id) {
		Admin admin = adminServiceInterface.getAdminProfile(id);
		return admin;
	}
	
	
	
	
	@GetMapping("/admin-get-total-customer")
	public List<Customer> getTotalCustomer() {
		return adminServiceInterface.getTotalCustomer();
	}
	
	
	
	@GetMapping("/admin-get-total-retailer")
	public List<Retailer> getTotalRetailer(){
		return adminServiceInterface.getTotalRetailer();
	}
	
	
	@PostMapping("/admin-approve-retailer")
	public RetailerRegisterStatus updateRetailerStatus(@RequestBody Retailer retailer) {
		Retailer retailer1 = retailerRepository.fetch(Retailer.class, retailer.getRetailerId());
		retailer1.setRetailerStatus('Y');
		adminServiceInterface.updateRetailerStatus(retailer1);
		
		//mail for approval of retailer
		System.out.println("preparing to send message ...");
		String message = "Congratulation ! \n You have been approved by webrash \n now you can login in through your email id and password";
		String subject = "Approval from Admin";
		String to = retailer.getEmail();
		String from = "webrashlti@gmail.com";
		
		SendEmail sendEmail = new SendEmail();
		sendEmail.sendEmail(message,subject,to,from);
		
		RetailerRegisterStatus status = new RetailerRegisterStatus();
		status.setStatus(true);
		status.setMessage("Retailer approved successfully");
		return status;
	}
	
	
	@PostMapping("/admin-reject-retailer")
	public RetailerRegisterStatus rejectRetailerStatus(@RequestBody Retailer retailer) {
		Retailer retailer1 = retailerRepository.fetch(Retailer.class, retailer.getRetailerId());
		retailer1.setRetailerStatus('N');
		adminServiceInterface.updateRetailerStatus(retailer1);
		RetailerRegisterStatus status = new RetailerRegisterStatus();
		status.setStatus(true);
		status.setMessage("Retailer rejected successfully");
		return status;
	}
	
}

	