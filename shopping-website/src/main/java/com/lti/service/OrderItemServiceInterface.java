package com.lti.service;

import java.util.List;

import com.lti.entity.OrderItem;

public interface OrderItemServiceInterface {

	public int addOrderItem(OrderItem orderItem);
	
	public List<OrderItem> fetchOrderItems(int orderId);	
}
