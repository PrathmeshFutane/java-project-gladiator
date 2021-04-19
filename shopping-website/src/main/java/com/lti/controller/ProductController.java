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
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ImageStatus;
import com.lti.dto.ProductImage;
import com.lti.dto.ProductStatus;
import com.lti.dto.RegisterStatus;
import com.lti.entity.Category;
import com.lti.entity.Product;
import com.lti.exception.CategoryServiceException;
import com.lti.exception.ProductServiceException;
import com.lti.service.CategoryService;
import com.lti.service.ProductService;
import com.lti.service.ProductServiceInterface;

import ch.qos.logback.core.status.Status;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductServiceInterface productServiceInterface;
	
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
			
			Product product = new Product();
			
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
			
			return productServiceInterface.fetchProduct();
	}
}
			
			
		
