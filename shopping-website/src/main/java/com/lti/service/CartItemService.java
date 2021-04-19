package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.CartItem;
import com.lti.repository.CartItemRepository;

@Service
@Transactional
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	public int addCartItem(CartItem cartItem) {

		CartItem updatedCartItem = (CartItem) cartItemRepository.save(cartItem);
		return updatedCartItem.getCartItemId();
	}
	
	

	public List<CartItem> fetchCartItems(int id) {
		return cartItemRepository.fetchAllCartItem(id);
	}
	
	
	
	
	
}
