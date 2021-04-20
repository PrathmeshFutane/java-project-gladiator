/**
 * Author 
 * Jeshma M
 * This is the service for payment controller.
 */

package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Payment;
import com.lti.repository.PaymentRepository;

@Service
public class PaymentService implements PaymentServiceInterface {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public int addPayment(Payment payment) {

		Payment updatedPayment = (Payment) paymentRepository.save(payment);
		return updatedPayment.getPaymentId();
	}
	
}
