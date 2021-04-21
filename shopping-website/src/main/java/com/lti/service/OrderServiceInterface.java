package com.lti.service;

import com.lti.entity.Cart;
import com.lti.entity.Order;

public interface OrderServiceInterface {

	public int addOrder(Order order);
	
//	public Cart deleteCart(int id);
	
	public Order deleteOrder(int id);
		
	}

