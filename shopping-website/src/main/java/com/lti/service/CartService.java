package com.lti.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Cart;
import com.lti.entity.Customer;
import com.lti.exception.CartServiceException;
import com.lti.exception.CustomerServiceException;
import com.lti.repository.CartRepository;

@Service
@Transactional
public class CartService implements CartServiceInterface{
	
	@Autowired
	private CartRepository cartRepository;

	public int addCart(Cart cart) {
		if(cartRepository.isCartPresent( cart.getCartId() )) {
			throw new CartServiceException("cart already exist");
		}
		else {
			Cart updatedCart = (Cart) cartRepository.save(cart);
			return updatedCart.getCartId();
		}		
	}
	
	

}
