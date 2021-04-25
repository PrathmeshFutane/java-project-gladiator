package com.lti.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.CartItem;
import com.lti.entity.Customer;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

import com.lti.exception.RetailerServiceException;
import com.lti.repository.RetailerRepository;



@Service
@Transactional
public class RetailerService implements RetailerServiceInterface {

	@Autowired
	private RetailerRepository retailerRepository;
	
	public int register(Retailer retailer) {
		if(retailerRepository.isRetailerPresent(retailer.getEmail()))
			throw new RetailerServiceException("Retailer already registered!");
		else {
			retailer.setPassword(Base64.getEncoder().encodeToString(retailer.getPassword().getBytes()));
			Retailer updatedRetailer = (Retailer) retailerRepository.save(retailer);
			//code to send email to the customer on successful registration will be here
			return updatedRetailer.getRetailerId();
		}
	}
	
	public Retailer login(String email, String password) {
		try {
			password = Base64.getEncoder().encodeToString(password.getBytes());
			int id = retailerRepository.fetchIdByEmailAndPassword(email, password);
			Retailer retailer= retailerRepository.fetch(Retailer.class, id);
			return retailer;
		}
		catch(EmptyResultDataAccessException e) {
		//catch(NoResultException e) {
			throw new RetailerServiceException("Invalid email/password");
		}
	}
	
	public List<Product> getProductsByRetailerId(int retailerId) {
		return retailerRepository.fetchByRetailerId(retailerId);		
	}
	
	public Retailer getRetailerProfile(int id) {
		return retailerRepository.fetch(Retailer.class, id);
	}
	
	
}
