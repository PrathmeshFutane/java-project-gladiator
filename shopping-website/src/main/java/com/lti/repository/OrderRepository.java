package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Cart;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;

@Repository
public class OrderRepository extends GenericRepository{

	public int deleteCart(int id) {
		return (Integer)
				entityManager
				.createQuery("delete from CartItem ci where ci.cart.cartId= :pk")
				.setParameter("pk",id)
				.executeUpdate();
	}
	
	
	public List<Order> fetchOrderByCustomerId(int customerId) {
		List<Order> list = entityManager
				.createQuery("select o from Order o where o.customer.customerId= :customerId")
				.setParameter("customerId",customerId)
				.getResultList();
		return list;
	}
	
//	public List<Order> fetchOrderByCustomerId(int customerId) {
//		List<Order> list = entityManager
//				.createQuery("select o from Order o where o.customer.customerId= :customerId and o.status= confirmed")
//				.setParameter("customerId",customerId)
//				.getResultList();
//		return list;
//	}
	
	
	
}
