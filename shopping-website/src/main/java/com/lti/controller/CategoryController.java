/*
 * 
 * @author Muskaan Pansare
 * This page is responsible for admin related functionalities
 *
 * 
 */

package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CategoryStatus;
import com.lti.dto.RegisterStatus;
import com.lti.entity.Category;
import com.lti.exception.CategoryServiceException;
import com.lti.service.CategoryService;
import com.lti.service.CategoryServiceInterface;

@RestController
@CrossOrigin
public class CategoryController {
	
	@Autowired
	private CategoryServiceInterface categoryServiceInterface;
	
	@PostMapping("/add-category")
	public CategoryStatus addCategory(@RequestBody Category category) {
		try {
			
			int id = categoryServiceInterface.addCategory(category);
			CategoryStatus status = new CategoryStatus();
			status.setStatus(true);
			status.setMessage("Category Added successful!");
			status.setRegisteredCategoryId(id);
			return status;
		}
		catch(CategoryServiceException e) {
			CategoryStatus status = new CategoryStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	

	@GetMapping("/get-category")
	public List<Category> getCategory(){
		return categoryServiceInterface.fetchCategory();
	}
}
