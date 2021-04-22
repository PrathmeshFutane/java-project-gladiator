package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Cart;
import com.lti.entity.OrderItem;

@Repository
public class OrderRepository extends GenericRepository{

	public int deleteCart(int id) {
		return (Integer)
				entityManager
				.createQuery("delete from CartItem ci where ci.cart.cartId= :pk")
				.setParameter("pk",id)
				.executeUpdate();
	}
	
	
	
	
}
