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
}
