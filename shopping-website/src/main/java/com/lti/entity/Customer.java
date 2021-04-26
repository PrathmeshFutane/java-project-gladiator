package com.lti.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shopping_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="shopping_customer_seq")
	@SequenceGenerator(sequenceName = "customer_sequence", allocationSize = 1, name ="shopping_customer_seq")
	@Column(name = "customer_id")
	private int customerId;
	
	private String name;
	private String email;
	
	private String password;
	private String address1;
	private String address2;
	
	@Column(name = "mobile_number")
	private long mobileNumber;
	
	@Column(name = "account_status")
	private char accountStatus;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	@JsonIgnore
	private Cart cart;
	
//	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//	@JoinColumn(name = "payment_id")
//	@JsonIgnore
//	private  Payment payment;
	
//	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
//	private List<Payment> payments;
	
	
//	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//	private List<Cart> carts;
	
//	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//	private List<Order> orders;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public char getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(char accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	

	

	
	
//	public Payment getPayment() {
//		return payment;
//	}
//
//	public void setPayment(Payment payment) {
//		this.payment = payment;
//	}



	


	
	
}
