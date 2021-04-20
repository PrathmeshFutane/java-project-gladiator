package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Category;
import com.lti.entity.Quantity;
import com.lti.service.QuantityService;

@RestController
@CrossOrigin
public class QuantityController {

	@Autowired
	private QuantityService quantityService;
	
	@GetMapping("/get-quantity")
	public List<Quantity> getCategory(){
		return quantityService.fetchQuantity();
	}
}
