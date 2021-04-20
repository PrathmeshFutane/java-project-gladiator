package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_payment")
public class Payment {

	@Id
	@GeneratedValue
	@Column(name = "payment_id")
	private int paymentId;
	
	 
}
