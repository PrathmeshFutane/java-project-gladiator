package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CartStatus;
import com.lti.entity.Cart;
import com.lti.exception.CartServiceException;
import com.lti.service.CartService;
import com.lti.service.CartServiceInterface;

@RestController
@CrossOrigin
public class CartController {

	@Autowired
	private CartServiceInterface cartServiceInterface;
	
	@PostMapping("/add-cart")
	public CartStatus addToCart(@RequestBody Cart cart) {
		try {
			int id = cartServiceInterface.addCart(cart);
			CartStatus status = new CartStatus();
			status.setStatus(true);
			status.setMessage("Cart Added successful!");
			status.setRegisteredCartId(id);
			return status;
		}
		catch(CartServiceException e) {
			CartStatus status = new CartStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
