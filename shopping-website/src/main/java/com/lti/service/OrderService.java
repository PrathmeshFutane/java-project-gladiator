package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Cart;

import com.lti.entity.Order;
import com.lti.repository.CartRepository;
import com.lti.repository.OrderRepository;

@Service
@Transactional
public class OrderService implements OrderServiceInterface{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	public int addOrder(Order order) {		
		Order updatedOrder = (Order) orderRepository.save(order);
		return updatedOrder.getOrderId();
	}
	
	
	public Cart deleteCartEg(int id) {
		return cartRepository.delete(Cart.class, id);
	}
	
	
	public Order deleteOrder(int id) {
		return orderRepository.delete(Order.class, id);
	}
	
	public int deleteCart(int id) {
		return orderRepository.deleteCart(id);
	}


	
	
}
