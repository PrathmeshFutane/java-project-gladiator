package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Cart;
import com.lti.entity.Customer;
import com.lti.repository.CartRepository;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
//	@Autowired
//	private Customer customer;

	public int register(Cart cart) {

		//Customer c = (Customer) cartRepository.fetch(Customer.class, customer.getCustomerId());
		Cart updatedCart = (Cart) cartRepository.save(cart);
		return updatedCart.getCartId();
	}

}
