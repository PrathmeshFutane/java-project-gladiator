package com.lti.service;

import com.lti.entity.Product;

public interface ProductServiceInterface {

	public void updateImage(int productId, String newFileName);
	
	public int addProduct(Product product);
}
