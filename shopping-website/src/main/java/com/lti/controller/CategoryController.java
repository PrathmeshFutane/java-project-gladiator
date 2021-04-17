package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CategoryStatus;
import com.lti.dto.RegisterStatus;
import com.lti.entity.Category;
import com.lti.exception.CategoryServiceException;
import com.lti.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	public CategoryStatus register(@RequestBody Category category) {
		try {
			//int otp = customer.getOtp();
			//int genOtp = session.getAttribute("otp");
			
			int id = categoryService.register(category);
			CategoryStatus status = new CategoryStatus();
			status.setStatus(true);
			status.setMessage("Category Added successful!");
			status.setRegisteredCategoryId(id);;
			return status;
		}
		catch(CategoryServiceException e) {
			CategoryStatus status = new CategoryStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	
}
