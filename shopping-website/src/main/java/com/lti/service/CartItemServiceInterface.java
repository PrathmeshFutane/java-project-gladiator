package com.lti.service;

import java.util.List;
import com.lti.dto.CartItemStatus;
import com.lti.entity.CartItem;

public interface CartItemServiceInterface {

	public int addCartItem(CartItem cartItem);
	
	public List<CartItem> fetchCartItems(int id);
	
	//public int deleteCart(int id) ;
	
	public CartItem deleteCart(int id);
	
	public CartItem updateQuantity(CartItem cartItems);
}
