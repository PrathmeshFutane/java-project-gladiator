package com.lti.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Category;
import com.lti.exception.CategoryServiceException;
import com.lti.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService implements CategoryServiceInterface{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public int addCategory(Category category) {
		if(categoryRepository.isCategoryPresent(category.getName()))
			throw new CategoryServiceException("Category already exist!");
		else {
			//category.setPassword(Base64.getEncoder().encodeToString(retailer.getPassword().getBytes()));
			Category updatedCategory = (Category) categoryRepository.save(category);
			//code to send email to the customer on successful registration will be here
			return updatedCategory.getCategoryId();
		}
	}
	
	public List<Category> fetchCategory(){
		return categoryRepository.fetch();
	}
	
	
}
