package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CartItemStatus;
import com.lti.entity.Cart;
import com.lti.entity.CartItem;
import com.lti.entity.Product;
import com.lti.exception.CartItemServiceException;
import com.lti.service.CartItemService;
import com.lti.service.CartItemServiceInterface;

@RestController
@CrossOrigin
public class CartItemController {

	@Autowired
	private CartItemServiceInterface cartItemServiceInterface;
	
	@PostMapping("/cart-item")
	public CartItemStatus generateCart(@RequestBody CartItem cartItem) {
		try {			
			int id = cartItemServiceInterface.addCartItem(cartItem);
			CartItemStatus status = new CartItemStatus();
			status.setStatus(true);
			status.setMessage("CartItem Added successful!");
			status.setRegisteredCartItemId(id);
			return status;
		}
		catch(CartItemServiceException e) {
			CartItemStatus status = new CartItemStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	
//	get all cart items
	@PostMapping("/get-all-cart-item")
	public List<CartItem> getAllCartItem(@RequestBody CartItem ci){
		List<CartItem> cartItems = cartItemServiceInterface.fetchCartItems(ci.getCart().getCartId());
		return cartItems;
	}
	
	//deleting the individual cart Item
//	@PostMapping("/delete-cart-item")
//	public int deleteCart(@RequestBody CartItem ci) {
//		CartItem ci = cartItemServiceInterface.deleteCart());
//	}
	
	@PostMapping("/delete-cart-item")
	public CartItemStatus deleteCart(@RequestBody CartItem cartItems) {
		CartItem ci = cartItemServiceInterface.deleteCart(cartItems.getCartItemId());
		//return ci.getCartItemId();
		CartItemStatus status = new CartItemStatus();
		status.setStatus(true);
		status.setMessage("CartItem Deleted successfully!");
		//status.setRegisteredCartItemId(id);
		return status;
	}
	
}
