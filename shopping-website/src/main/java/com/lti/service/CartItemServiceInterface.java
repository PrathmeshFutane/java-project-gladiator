package com.lti.service;

import java.util.List;

import com.lti.entity.CartItem;

public interface CartItemServiceInterface {

	public int addCartItem(CartItem cartItem);
	
	public List<CartItem> fetchCartItems(int id);
}
