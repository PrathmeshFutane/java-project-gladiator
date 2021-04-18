package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CartRepository extends GenericRepository{

//	public int fetchByCart(int id) {
//		return (Integer)
//				entityManager
//				.createQuery("select c from Cart c join fetch c.cartItem i where c.customer.id = :pk")
//				.setParameter("pk",id)
//				
//				.getSingleResult();
//	}
	
	
	public boolean isCartPresent(int cartId) {
		return (Long) 
				entityManager
				.createQuery("select count(c.cartId) from Cart c where c.cartId =: id")
				.setParameter("id", cartId)
				.getSingleResult() == 1 ? true : false;		
	}
	
	
}
