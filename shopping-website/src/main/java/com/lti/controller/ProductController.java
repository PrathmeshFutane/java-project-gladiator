	package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CartItemStatus;
import com.lti.dto.ImageStatus;
import com.lti.dto.ProductImage;
import com.lti.dto.ProductStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.RetailerRegisterStatus;
import com.lti.entity.Category;
import com.lti.entity.Product;
import com.lti.exception.CategoryServiceException;
import com.lti.exception.ProductServiceException;
import com.lti.repository.ProductRepository;
import com.lti.service.CategoryService;
import com.lti.service.ProductService;
import com.lti.service.ProductServiceInterface;

import ch.qos.logback.core.status.Status;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductServiceInterface productServiceInterface;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/add-product")
	public ProductStatus addProduct(@RequestBody Product product) {
		try {
			//int otp = customer.getOtp();
			//int genOtp = session.getAttribute("otp");
			
			int id = productServiceInterface.addProduct(product);
			ProductStatus status = new ProductStatus();
			status.setStatus(true);
			status.setMessage("Product Added successful!");
			status.setRegisteredProductId(id);
			return status;
		}
		catch(ProductServiceException e) {
			ProductStatus status = new ProductStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	@PostMapping("/upload-image")
	public ImageStatus uploadImage(ProductImage productImage) {
		ImageStatus istatus = new ImageStatus();
		int productId = productImage.getProductId();
		
		String imgUploadLocation = "d:/uploads/";
		String uploadedFileName = productImage.getProductImg().getOriginalFilename();
		String newFileName = productId + "-" + uploadedFileName;
		String targetFileName = imgUploadLocation + newFileName;
		
		try {
			FileCopyUtils.copy(productImage.getProductImg().getInputStream(), new FileOutputStream(targetFileName));
		}
		catch(IOException e) {
			e.printStackTrace(); //hope no error would occur
			istatus.setStatus(false);
			istatus.setMessage("Profilepic upload failed!");
		}
		
		productServiceInterface.updateImage(productId, newFileName);
		
		istatus.setStatus(true);
		istatus.setMessage("Profilepic uploaded successfully!");
		return istatus;
	}
	
	
	
	
	//object having product information and image as well
	@GetMapping("all-products")
	public List<Product> getAllProduct(HttpServletRequest request) {
		
//		return categoryServiceInterface.fetchCategory();
			
			List<Product> products =  productServiceInterface.fetchProducts();
			
			for(Product product : products) {
				String projPath = request.getServletContext().getRealPath("/");
				System.out.println(projPath);
				
				String tempDownloadPath = projPath + "/downloads/";
				File f = new File(tempDownloadPath);
				if(!f.exists())
					f.mkdir();
				
				String targetFile = tempDownloadPath + product.getImage();
				
				//reading the original location where the image is present
				String uploadedImagesPath = "d:/uploads/";
				String sourceFile = uploadedImagesPath + product.getImage();
				
				try {
					FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
				}
				catch (IOException e) {
					e.printStackTrace(); //hoping for no error will occur
				}
			}
			return products;
	}
	
	//get product by category
	@GetMapping("/get-products-by-category")
	public List<Product> getAllProductByCategory(@RequestParam("categoryId") int categoryId){
		List<Product> products =  productServiceInterface.getProductByCategory(categoryId);
		return products;
		
	}
	
	
	@GetMapping("/search")
	public List<Product> check(@RequestParam("keyword") String keyword) {
		List<Product> products = productServiceInterface.getByKeyword(keyword);
		return products;
	}
	
	
	@GetMapping("/product-filter")
	public List<Product> filter(@RequestParam("price1") int price1 , @RequestParam("price2") int price2) {
		List<Product> products = productServiceInterface.getByRange(price1, price2);
		return products;
	}
	
	@GetMapping("/ascending-order")
	public List<Product> ascendingOrder() {
		List<Product> products = productServiceInterface.getByAscending();
		return products;
	}
	
	@GetMapping("/descending-order")
	public List<Product> descendingOrder() {
		List<Product> products = productServiceInterface.getByDescending();
		return products;
	}
	

	@PostMapping("/update-stock")
	public ProductStatus updateStock(@RequestBody Product product) {
		
		Product product1 = productRepository.fetch(Product.class,product.getProductId());
		product1.setStock(product.getStock());
		productServiceInterface.updateStock(product1);
		ProductStatus status = new ProductStatus();
		status.setStatus(true);
		status.setMessage("Stock updated successfully!");
		return status;
		
	}
	
	
	@GetMapping("/random-products")
	public List<Product> randomProducts(){
		return productServiceInterface.randomProducts();
	}
	
}
			
			
		
