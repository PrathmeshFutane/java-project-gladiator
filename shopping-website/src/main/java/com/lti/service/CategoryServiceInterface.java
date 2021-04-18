package com.lti.service;

import java.util.List;

import com.lti.entity.Category;

public interface CategoryServiceInterface {
	
	public int addCategory(Category category);
	
	public List<Category> fetchCategory();
}
