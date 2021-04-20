package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.CartItem;

@Repository
public class CartItemRepository extends GenericRepository{

	public List<CartItem> fetchAllCartItem(int id) {
		List<CartItem> list = entityManager.createQuery("select ci from CartItem ci where ci.cart.cartId = :pk")
				.setParameter("pk", id)
				.getResultList();
		return list;		
	}
	
	
	public boolean isCartItemPresent(int id) {
		return (Long) 
				entityManager
				.createQuery("select count(ci.id) from CartItem ci where ci.product.productId= :pk")
				.setParameter("pk", id)
				.getSingleResult() == 1 ? true : false;		
	}
	
	public CartItem deleteCart(int id) {
		return (CartItem)
				entityManager
				.createQuery("Delete from CartItem ci where ci.cart.cartId= :pk")
				.setParameter("pk",id)
				.getSingleResult();
	}
	
}
