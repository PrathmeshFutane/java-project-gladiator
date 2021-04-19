package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.CartItem;

@Repository
public class CartItemRepository extends GenericRepository{

	public List<CartItem> fetchAllCartItem() {
		List<CartItem> list = entityManager.createQuery("select c from CartItem c").getResultList();
		return list;
		
		
	}
}
