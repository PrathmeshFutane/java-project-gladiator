package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.CartItem;
import com.lti.entity.Order;
import com.lti.repository.OrderRepository;

@Service
@Transactional
public class OrderService implements OrderServiceInterface{

	@Autowired
	private OrderRepository orderRepository;
	
	public int addOrder(Order order) {		
		Order updatedOrder = (Order) orderRepository.save(order);
		return updatedOrder.getOrderId();
	}
	
	
	
}
