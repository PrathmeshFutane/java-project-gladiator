package com.lti.dto;
import org.springframework.web.multipart.MultipartFile;

public class ProductImage {
	private int retailerId;
	private MultipartFile productImg;

	
	public int getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}
	public MultipartFile getProductImg() {
		return productImg;
	}
	public void setProductImg(MultipartFile productImg) {
		this.productImg = productImg;
	}
	
}
