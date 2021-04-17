package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ProductStatus;
import com.lti.dto.RegisterStatus;
import com.lti.entity.Category;
import com.lti.entity.Product;
import com.lti.exception.CategoryServiceException;
import com.lti.exception.ProductServiceException;
import com.lti.service.CategoryService;
import com.lti.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ProductStatus register(@RequestBody Product product) {
		try {
			//int otp = customer.getOtp();
			//int genOtp = session.getAttribute("otp");
			
			int id = productService.register(product);
			ProductStatus status = new ProductStatus();
			status.setStatus(true);
			status.setMessage("Product Added successful!");
			status.setRegisteredProductId(id);;
			return status;
		}
		catch(ProductServiceException e) {
			ProductStatus status = new ProductStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
