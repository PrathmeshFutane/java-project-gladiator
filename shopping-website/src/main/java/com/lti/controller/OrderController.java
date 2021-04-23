package com.lti.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CartItemStatus;
import com.lti.dto.OrderStatus;
import com.lti.dto.RetailerRegisterStatus;
import com.lti.entity.Cart;
import com.lti.entity.CartItem;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.exception.CartServiceException;
import com.lti.repository.CartItemRepository;
import com.lti.repository.CartRepository;
import com.lti.repository.OrderRepository;
import com.lti.repository.RetailerRepository;
import com.lti.service.CartItemServiceInterface;
import com.lti.service.OrderItemServiceInterface;
import com.lti.service.OrderServiceInterface;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderServiceInterface orderServiceInterface;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private RetailerRepository retailerRepository;
	
	@PostMapping("/place-order")
	public OrderStatus placeOrder(@RequestBody Order order) {
		try {
			int finalTotal = 0;
			int totalRevenue = 0;
			LocalDate today = LocalDate.now(); 
			order.setOrderDate(today);
			order.setDeliveryDate(today.plusDays(5));
			order.setOrderStatus("Pending");			
			
			int customerId =  order.getCustomer().getCustomerId();
			Cart c = cartRepository.fetchByCart(customerId);
			List<OrderItem> list = new ArrayList<OrderItem>();
			for(CartItem cartItems : c.getCartItems()) {
				OrderItem orderItems = new OrderItem();
				orderItems.setProduct(cartItems.getProduct());
				orderItems.setQuantity(cartItems.getQuantity());
				
				Product product = orderItems.getProduct();
				
				int finalQuantity = product.getStock() - orderItems.getQuantity();
				product.setStock(finalQuantity);
				
				int total = product.getUnitPrice() * orderItems.getQuantity();
				
				
				
				orderItems.setSubTotalPrice(total);
				orderItems.setOrder(order);
				list.add(orderItems);
				
				finalTotal = finalTotal + total;
				
				//The corrected revenue generated for retailers after a customer places an order and make payment.
				
				int retailerId = orderItems.getProduct().getRetailer().getRetailerId();
				totalRevenue= totalRevenue + total;
				
				Retailer retailer = retailerRepository.fetch(Retailer.class, retailerId);
				retailer.setRevenue(totalRevenue);
				
				//end of retailer revenue logic
				
			}
			order.setTotalPrice(finalTotal);
			order.setOrderItems(list);
			int id = orderServiceInterface.addOrder(order);
			
			orderServiceInterface.deleteCart(c.getCartId());
		
			OrderStatus status = new OrderStatus();
			status.setStatus(true);
			status.setMessage("Order Added successful!");
			status.setRegisteredOrderId(id);
			return status;			
		}
		catch(CartServiceException e) {
			OrderStatus status = new OrderStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
		
//	@GetMapping("/cancel-order")
//	public OrderStatus cancelOrder(@RequestParam("orderId") int id) {
//		Order o = orderServiceInterface.deleteOrder(id);
//		//return ci.getCartItemId();
//		OrderStatus status = new OrderStatus();
//		status.setStatus(true);
//		status.setMessage("Order cancelled successfully!");
//		//status.setRegisteredCartItemId(id);
//		return status;
//	}
	
	
	
	@GetMapping("/get-order-by-customer")
	public List<Order> getAllOrderByCustomer(@RequestParam("customerId") int customerId){
		List<Order> orders = orderServiceInterface.getOrderByCustomerId(customerId);
		return orders;
	}
	
	@PostMapping("/cancel-order")
	public OrderStatus cancelOrder(@RequestBody Order order) {
		Order o = orderRepository.fetch(Order.class, order.getOrderId());
		o.setOrderStatus("Cancelled");
		orderServiceInterface.cancelOrder(o);
		OrderStatus status = new OrderStatus();
		status.setStatus(true);
		status.setMessage("Order confirmed successfully");
		return status;
	}
	
	@PostMapping("/confirm-order")
	public OrderStatus confirmOrder(@RequestBody Order order) {
		Order o = orderRepository.fetch(Order.class, order.getOrderId());
		o.setOrderStatus("Confirmed");
		orderServiceInterface.confirmOrder(o);
		OrderStatus status = new OrderStatus();
		status.setStatus(true);
		status.setMessage("Order confirmed successfully");
		return status;
	}
	
}
