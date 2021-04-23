package com.lti.service;

import java.util.List;

import com.lti.entity.Cart;
import com.lti.entity.Order;

public interface OrderServiceInterface {

	public int addOrder(Order order);
	
	public int deleteCart(int id);
	
	public Order deleteOrder(int id);
	
	public List<Order> getOrderByCustomerId(int customerId);
	
	public Order cancelOrder(Order order);
	
	public Order confirmOrder(Order order);
		
	}

