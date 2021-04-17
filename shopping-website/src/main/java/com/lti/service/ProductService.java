package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Product;
import com.lti.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public int register(Product product) {

			//category.setPassword(Base64.getEncoder().encodeToString(retailer.getPassword().getBytes()));
		Product updatedProduct = (Product) productRepository.save(product);
			//code to send email to the customer on successful registration will be here
		return updatedProduct.getProductId();
		}
	}
