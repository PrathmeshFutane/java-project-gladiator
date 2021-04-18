package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Cart;
import com.lti.repository.CartRepository;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	public int register(Cart cart) {

		Cart updatedCart = (Cart) cartRepository.save(cart);
		return updatedCart.getCartId();
	}

}
