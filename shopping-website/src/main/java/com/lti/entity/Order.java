package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shopping_order")
public class Order {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="shopping_order_seq")
	@SequenceGenerator(sequenceName = "order_sequence", allocationSize = 1, name ="shopping_order_seq")
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@Column(name = "total_price")
	private long totalPrice;
	
	@Column(name = "delivery_date")
	private LocalDate deliveryDate;
	
	@Column(name = "order_status")
	private char orderStatus;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderItem> orderItems;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	@JsonIgnore
	private  Payment payment;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public char getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(char orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	

	

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
