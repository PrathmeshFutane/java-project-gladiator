package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Cart;
import com.lti.entity.OrderItem;

@Repository
public class OrderRepository extends GenericRepository{

	public Cart deleteCart(int id) {
		return (Cart)
				entityManager
				.createQuery("Delete from Cart c where c.customer.customerId= :pk")
				.setParameter("pk",id)
				.getSingleResult();
	}
	
	
}
