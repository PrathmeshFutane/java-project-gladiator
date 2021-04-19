package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminStatus;
import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RegisterStatus;
import com.lti.entity.Admin;
import com.lti.exception.AdminServiceException;
import com.lti.service.AdminServiceInterface;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminServiceInterface adminServiceInterface;
	
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
}
