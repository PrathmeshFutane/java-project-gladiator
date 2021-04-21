package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Cart;
import com.lti.entity.CartItem;
import com.lti.exception.CartItemServiceException;
import com.lti.repository.CartItemRepository;

@Service
@Transactional
public class CartItemService implements CartItemServiceInterface{

	@Autowired
	private CartItemRepository cartItemRepository;
	
	public int addCartItem(CartItem cartItem) {
		if(cartItemRepository.isCartItemPresent(cartItem.getProduct().getProductId(), cartItem.getCart().getCartId())) {
			throw new CartItemServiceException("Product already exist in cart");
		}
		else {
			CartItem updatedCartItem = (CartItem) cartItemRepository.save(cartItem);
			return updatedCartItem.getCartItemId();
		}
	
	}

	public List<CartItem> fetchCartItems(int id) {
		return cartItemRepository.fetchAllCartItem(id);
	}
	
//	public int deleteCart(int id) {
//		return cartItemRepository.deleteCart(id);
//	}
	
	public CartItem deleteCart(int id) {
		return cartItemRepository.delete(CartItem.class, id);
	}
	
}
