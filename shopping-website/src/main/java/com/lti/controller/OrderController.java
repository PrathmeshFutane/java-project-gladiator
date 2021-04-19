package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.OrderStatus;
import com.lti.entity.Order;
import com.lti.exception.CartServiceException;
import com.lti.service.OrderServiceInterface;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderServiceInterface orderServiceInterface;
	
	@PostMapping("/place-order")
	public OrderStatus addOrder(@RequestBody Order order) {
		try {
			int id = orderServiceInterface.addOrder(order);
			OrderStatus status = new OrderStatus();
			status.setStatus(true);
			status.setMessage("Order Added successful!");
			status.setRegisteredOrderId(id);
			return status;
		}
		catch(CartServiceException e) {
			OrderStatus status = new OrderStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
