/**
 * Author 
 * Jeshma M
 * This is the entity for payment controller.
 */

package com.lti.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="shopping_payment_seq")
	@SequenceGenerator(sequenceName = "payment_sequence", allocationSize = 1, name ="shopping_payment_seq")
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name = "bill_amount")
	private long billAmount;
	
	@Column(name="card_number")
	private long cardNumber;
	
	@Column(name="expiry_month")
	private int expiryMonth;
	
	@Column(name = "expiry_year")
	private int expiryYear;
	
	private int cvv;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "name_on_card")
	private String nameOnCard;
	
	
//	@OneToOne
//	@JoinColumn(name = "customer_id")
//	private Customer customer;
	
//	@ManyToOne
//	@JoinColumn(name = "customer_id")
//	private Customer customer;
	
//	@OneToOne
//	@JoinColumn(name = "order_id")
//	private Order order;
	
	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	

	public long getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;

	}

	

	
	
	
}
