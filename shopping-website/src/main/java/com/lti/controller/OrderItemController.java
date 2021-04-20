//package com.lti.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lti.dto.OrderItemStatus;
//import com.lti.entity.OrderItem;
//import com.lti.exception.OrderItemServiceException;
//import com.lti.service.OrderItemServiceInterface;
//import com.lti.service.OrderItemServiceInterface;
//
//@RestController
//@CrossOrigin
//public class OrderItemController {
//
//	@Autowired
//	private OrderItemServiceInterface orderItemServiceInterface;
//	
//	@PostMapping("/order-item")
//	public OrderItemStatus addOrderItem(@RequestBody OrderItem orderItem) {
//		try {
//			//int otp = customer.getOtp();
//			//int genOtp = session.getAttribute("otp");
//			
////			int id = OrderItemServiceInterface.addOrderItem(orderItem);
//			OrderItemStatus status = new OrderItemStatus();
//			status.setStatus(true);
//			status.setMessage("OrderItem Added successful!");
////			status.setRegisteredOrderItemId(id);;
//			return status;
//		}
//		catch(OrderItemServiceException e) {
//			OrderItemStatus status = new OrderItemStatus();
//			status.setStatus(false);
//			status.setMessage(e.getMessage());
//			return status;
//		}
//	}
//}
