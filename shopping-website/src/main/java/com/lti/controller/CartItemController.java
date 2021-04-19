package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CartItemStatus;
import com.lti.entity.CartItem;
import com.lti.entity.Product;
import com.lti.exception.CartItemServiceException;
import com.lti.service.CartItemService;

@RestController
@CrossOrigin
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@PostMapping("/cart-item")
	public CartItemStatus register(@RequestBody CartItem cartItem) {
		try {
			//int otp = customer.getOtp();
			//int genOtp = session.getAttribute("otp");
			
			int id = cartItemService.addCartItem(cartItem);
			CartItemStatus status = new CartItemStatus();
			status.setStatus(true);
			status.setMessage("CartItem Added successful!");
			status.setRegisteredCartItemId(id);;
			return status;
		}
		catch(CartItemServiceException e) {
			CartItemStatus status = new CartItemStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	
//	get all items
	@GetMapping("/get-all-cart-item")
	public List<CartItem> getAllCartItem(){
		
		List<CartItem> cartItems = cartItemService.fetchCartItems();
		return cartItems;
		
	}
}
