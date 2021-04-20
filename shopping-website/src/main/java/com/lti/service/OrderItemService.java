//package com.lti.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.lti.entity.Cart;
//import com.lti.entity.CartItem;
//import com.lti.entity.Order;
////import com.lti.entity.Customer;
////import com.lti.entity.Order;
//import com.lti.entity.OrderItem;
////import com.lti.repository.CartItemRepository;
//import com.lti.repository.CartRepository;
////import com.lti.repository.CustomerRepository;
////import com.lti.repository.OrderItemRepository;
//
//@Service
//@Transactional
//public class OrderItemService implements OrderItemServiceInterface{
//
////	@Autowired
////	private OrderItemRepository orderItemRepository;
////	
////
////	
////	@Autowired
////	private CartItemRepository cartItemRepository;
////	
////	
//	
//	public int addOrderItem(Order order) {		
//		Order updatedOrder = (Order) orderRepository.save(order);
//		return updatedOrder.getOrderId();
//	}
//	
//	
////	public int addOrderItem(OrderItem orderItem){
////		//Customer c = new Customer();
////		//Customer c = customerRepository.fetch(Customer.class, c);
////		Cart ca = cartRepository.fetchByCart(c);
////		
////		for(CartItem ci : ca.getCartItems()) {
////					//CartItem ci = (CartItem) dao.fetch(CartItem.class, 162);
////			OrderItem oi = new OrderItem();
////			//CartItem ci = new CartItem();
////			orderItem.setProduct(ci.getProduct());
////			orderItem.setQuantity(ci.getQuantity());
////			Order order = new Order();
////			orderItem.setOrder(order);
////			OrderItem  updatedOrderItem  = (OrderItem ) orderItemRepository.save(orderItem);
////			return updatedOrderItem.getOrderItemsId();
////	}
////	
////	}
//	
////	public int addOrderItem(OrderItem orderItem){
////		
////		
////		Cart ca = cartRepository.fetchByCart(c.getCustomerId());
////		
////		for(CartItem ci : ca.getCartItems()) {
////					//CartItem ci = (CartItem) dao.fetch(CartItem.class, 162);
////			OrderItem oi = new OrderItem();
////			
////			CartItem ci = cartItemRepository.fetch(CartItem.class, 270);
////			//CartItem ci = new CartItem();
////			orderItem.setProduct(ci.getProduct());
////			orderItem.setQuantity(ci.getQuantity());
////			Order order = new Order();
////			orderItem.setOrder(order);
////			OrderItem  updatedOrderItem  = (OrderItem ) orderItemRepository.save(orderItem);
////			return updatedOrderItem.getOrderItemsId();
////	}
//	
//	
//	
//	}
//	
//
