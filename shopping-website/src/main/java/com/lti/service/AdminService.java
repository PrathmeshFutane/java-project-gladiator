package com.lti.service;

import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.exception.AdminServiceException;
import com.lti.repository.AdminRepository;

@Service
@Transactional
public class AdminService implements AdminServiceInterface{
	
	@Autowired
	private AdminRepository adminRepository;
	
	public int register(Admin admin) {
		if(adminRepository.isAdminPresent(admin.getEmail())) {
			throw new AdminServiceException("Admin already register");
		}
		else {
			admin.setPassword(Base64.getEncoder().encodeToString(admin.getPassword().getBytes()));
			Admin updatedAdmin = (Admin) adminRepository.save(admin);
			return updatedAdmin.getAdminId();
		}
	}
	
	public Admin login(String email, String password) {
		try {
			password = Base64.getEncoder().encodeToString(password.getBytes());
			int id = adminRepository.fetchIdByEmailAndPassword(email, password);
			Admin admin = adminRepository.fetch(Admin.class, id);
			return admin;
		}
		catch(EmptyResultDataAccessException e) {
	
			throw new AdminServiceException("Invalid email/password");
		}
	}
	
	public Admin getAdminProfile(int id) {
		return adminRepository.fetch(Admin.class, id);
	}
}

