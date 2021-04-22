package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Product;
import com.lti.repository.ProductRepository;

@Service
@Transactional
public class ProductService implements ProductServiceInterface{

	@Autowired
	private ProductRepository productRepository;
	
	
	public int addProduct(Product product) {

			//category.setPassword(Base64.getEncoder().encodeToString(retailer.getPassword().getBytes()));
		Product updatedProduct = (Product) productRepository.save(product);
			//code to send email to the customer on successful registration will be here
		return updatedProduct.getProductId();
	}
	
	public void updateImage(int productId, String newFileName) {
		Product product = productRepository.fetch(Product.class, productId);
		product.setImage(newFileName);
		productRepository.save(product);
	}

	@Override
	public List<Product> fetchProducts() {
		return productRepository.fetch();		
	}
	
	public List<Product> getProductByCategory(int categoryId) {
		return productRepository.fetchByCategory(categoryId);		
	}
	
	
	public List<Product> getByKeyword(String keyword){
		return productRepository.fetchByKeyword(keyword);
	}
	
	
	
	
	
	
	
	
}



