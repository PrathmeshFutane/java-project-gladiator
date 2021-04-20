package com.lti.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.OrderStatus;
import com.lti.entity.Cart;
import com.lti.entity.CartItem;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
import com.lti.exception.CartServiceException;
import com.lti.repository.CartRepository;
import com.lti.repository.OrderRepository;
import com.lti.service.OrderServiceInterface;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderServiceInterface orderServiceInterface;
	
	@Autowired
	private CartRepository cartRepository;
	
	@PostMapping("/place-order")
	public OrderStatus placeOrder(@RequestBody Order order) {
		try {
			
			LocalDate today = LocalDate.now(); 
			order.setOrderDate(today);
			order.setShippingDate(today.plusDays(5));
			order.setOrderStatus('Y');
			
			int customerId =  order.getCustomer().getCustomerId();
			Cart c = cartRepository.fetchByCart(customerId);
			List<OrderItem> list = new ArrayList<OrderItem>();
			for(CartItem cartItems : c.getCartItems()) {
				OrderItem orderItems = new OrderItem();
				orderItems.setProduct(cartItems.getProduct());
				orderItems.setQuantity(cartItems.getQuantity());
				
				Product product = orderItems.getProduct();  
				int total = product.getUnitPrice() * orderItems.getQuantity();
				orderItems.setTotalPrice(total);
				orderItems.setOrder(order);
				list.add(orderItems);
			}
			order.setOrderItems(list);
			int id = orderServiceInterface.addOrder(order);
			OrderStatus status = new OrderStatus();
			status.setStatus(true);
			status.setMessage("Order Added successful!");
			status.setRegisteredOrderId(id);
			return status;
			
			
			
					
					

			
			
			
			
//			Cart cart = new Cart();
//	
//			int cart1 = cart.getCustomer().getCustomerId();
//			
//			List<CartItem> cartItems = cart1.getCartItems();
//			
//			
//			for(CartItem cartItems1 : cartItems ) {
//				System.out.println(cartItems1.getQuantity() + "," + cartItems1.getProduct().getProductId());
//			}
//			int id = orderServiceInterface.addOrder(order);
//			OrderStatus status = new OrderStatus();
//			status.setStatus(true);
//			status.setMessage("Order Added successful!");
//			//status.setRegisteredOrderId(id);
//			return status;
		}
		catch(CartServiceException e) {
			OrderStatus status = new OrderStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
