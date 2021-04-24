package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.OrderItemStatus;
import com.lti.entity.CartItem;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.exception.OrderItemServiceException;
import com.lti.service.OrderItemServiceInterface;


/**
 * 
 * @author Shreyash Mhashilkar
 * This is page responsible for getting all order items
 *
 */
@RestController
@CrossOrigin
public class OrderItemController {

	@Autowired
	private OrderItemServiceInterface orderItemServiceInterface;
	
//	get all order items
	@PostMapping("/get-all-order-item")
	public List<OrderItem> getAllOrderItem(@RequestBody OrderItem oi){
		List<OrderItem> orderItem = orderItemServiceInterface.fetchOrderItems(oi.getOrder().getOrderId());
		return orderItem;
	}
	
//	get orderitem information according to order
	@GetMapping("/get-orderId-by-order")
	public List<OrderItem> getAllOrderItemByOrder(@RequestParam("orderId") int orderId){
		List<OrderItem> orderItems = orderItemServiceInterface.getOrderItemByOrderId(orderId);
		return orderItems;
	}
	
	
	
}







































//	@PostMapping("/order-item")
//	public OrderItemStatus addOrderItem(@RequestBody OrderItem orderItem) {
//		try {
//			
//			
//			int id = orderItemServiceInterface.addOrderItem(orderItem);
//			OrderItemStatus status = new OrderItemStatus();
//			status.setStatus(true);
//			status.setMessage("OrderItem Added successful!");
//			status.setRegisteredOrderItemId(id);;
//			return status;
//		}
//		catch(OrderItemServiceException e) {
//			OrderItemStatus status = new OrderItemStatus();
//			status.setStatus(false);
//			status.setMessage(e.getMessage());
//			return status;
//		}
//	}

