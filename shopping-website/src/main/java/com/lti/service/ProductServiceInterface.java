package com.lti.service;

import java.util.List;

import com.lti.entity.Product;

public interface ProductServiceInterface {

	public void updateImage(int productId, String newFileName);
	
	public int addProduct(Product product);
	
	public List<Product> fetchProducts();
	
	public List<Product> getProductByCategory(int categoryId);
}
