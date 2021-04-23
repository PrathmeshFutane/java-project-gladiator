/**
 * Author 
 * Jeshma M
 * This is the service for payment controller.
 */

package com.lti.service;

import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Customer;
import com.lti.entity.Payment;
import com.lti.exception.CustomerServiceException;
import com.lti.exception.PaymentServiceException;
import com.lti.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService implements PaymentServiceInterface {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public int addPayment(Payment payment) {
		if(paymentRepository.isOrderIdPresent(payment.getOrder().getOrderId())) {
			throw new PaymentServiceException("Payment already done");
		}
		else {
			Payment updatedPayment = (Payment) paymentRepository.save(payment);
			return updatedPayment.getPaymentId();
		}		
	}
	
	
	
}
