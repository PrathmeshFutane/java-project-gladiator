/**
 * Author 
 * Jeshma M
 * This is the controller for payment.
 */

package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.PaymentStatus;
import com.lti.entity.Order;
import com.lti.entity.Payment;
import com.lti.exception.PaymentServiceException;
import com.lti.repository.OrderRepository;
import com.lti.service.PaymentService;
import com.lti.service.PaymentServiceInterface;

@RestController
@CrossOrigin
public class PaymentController {
	
	@Autowired
	private PaymentServiceInterface paymentServiceInterface;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@PostMapping("/add-payment")
	public PaymentStatus addPayment(@RequestBody Payment payment) {
		try {
			
			Order order = orderRepository.fetch(Order.class, payment.getOrder().getOrderId());
			payment.setBillAmount(order.getTotalPrice());
			
			int id = paymentServiceInterface.addPayment(payment);
			PaymentStatus status = new PaymentStatus();
			status.setStatus(true);
			status.setMessage("Payment successful!");
			status.setRegisteredPaymentId(id);
			return status;
		}
		catch(PaymentServiceException e) {
			PaymentStatus status = new PaymentStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
