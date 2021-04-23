/**
 * Author 
 * Jeshma M
 * This is the repository for payment.
 */


package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository extends GenericRepository{
	
	
	public boolean isOrderIdPresent(int orderId) {
		return(Long)
				entityManager
				.createQuery("select count(p.paymentId) from Payment p where p.order.orderId =:id")
				.setParameter("id", orderId)
				.getSingleResult() == 1 ? true : false;	
				}
	
	
	
	
	

}
