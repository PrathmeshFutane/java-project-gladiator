package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.entity.Cart;


@Repository
public class CartRepository extends GenericRepository{
	//most important
	public Cart fetchByCart(int id) {
		return (Cart)
				entityManager
				.createQuery("select c from Cart c join fetch c.cartItems ci where c.customer.id = :pk")
				//.createQuery("select ct from Cart ct join Customer c where c.customer.id = :pk")
				.setParameter("pk",id)
				.getSingleResult();
	}
	
	
	public boolean isCartPresent(int cartId) {
		return (Long) 
				entityManager
				.createQuery("select count(c.cartId) from Cart c where c.cartId =: id")
				.setParameter("id", cartId)
				.getSingleResult() == 1 ? true : false;		
	}
	
	
	
}
