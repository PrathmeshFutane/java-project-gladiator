package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Order;
import com.lti.entity.OrderItem;

@Repository
public class OrderItemRepository extends GenericRepository{

	public List<OrderItem> fetchAllOrderItem(int orderId) {
		List<OrderItem> list = entityManager
								.createQuery("select oi from OrderItem oi where oi.order.orderId = :orderId")
								.setParameter("orderId", orderId)
								.getResultList();
		return list;		
	}
	
	
	public List<OrderItem> fetchOrderItemByOrderId(int orderId) {
		List<OrderItem> list = entityManager
				.createQuery("select oi from OrderItem oi where oi.order.orderId= :orderId")
				.setParameter("orderId",orderId)
				.getResultList();
		return list;
	}
	
}
