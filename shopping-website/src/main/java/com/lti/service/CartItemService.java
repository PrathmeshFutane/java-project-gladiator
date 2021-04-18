package com.lti.service;

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
	
	public int register(CartItem cartItem) {

		CartItem updatedCartItem = (CartItem) cartItemRepository.save(cartItem);
		return updatedCartItem.getCartItemId();
	}
}
