package com.lti.service;

import java.util.List;

import com.lti.entity.Product;

public interface ProductServiceInterface {

	public void updateImage(int productId, String newFileName);
	
	public int addProduct(Product product);
	
	public List<Product> fetchProducts();
	
	public List<Product> getProductByCategory(int categoryId);
	
	public List<Product> getByKeyword(String keyword);
	
	public List<Product> getByRange(int price1, int price2);
	
	public List<Product> getByAscending();
	
	public List<Product> getByDescending();
	
	public Product updateStock(Product product);
}
