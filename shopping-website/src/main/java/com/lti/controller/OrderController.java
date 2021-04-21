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
import com.lti.entity.Cart;
import com.lti.entity.CartItem;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
import com.lti.exception.CartServiceException;
import com.lti.repository.CartItemRepository;
import com.lti.repository.CartRepository;
import com.lti.repository.OrderRepository;
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
	private CartItemRepository cartItemRepository;
	
	@PostMapping("/place-order")
	public OrderStatus placeOrder(@RequestBody Order order) {
		try {
			
			LocalDate today = LocalDate.now(); 
			order.setOrderDate(today);
			order.setShippingDate(today.plusDays(5));
			order.setOrderStatus('Y');
			//order.setTotalPrice(order.getTotalPrice());
	
//			OrderItem oi = new OrderItem();
//			for(Order o : oi.getTotalPrice() ) {
//				int totalPrice = totalPrice + o
//			}
//			order.setTotalPrice(totalPrice);
			
			
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
				orderItems.setTotalPrice(total);
				orderItems.setOrder(order);
				list.add(orderItems);
			}
			order.setOrderItems(list);
			int id = orderServiceInterface.addOrder(order);
			
			//CartItemRepository
//			cartItemRepository.deleteCart(c.getCartId());
//			System.out.println("delete done");
			
			//GenericRepository
//			cartRepository.delete(Cart.class, c.getCartId());
//			System.out.println("delete done");public CartItemStatus deleteCart(@RequestBody CartItem cartItems) {
			
			//cart delete 
			//Cart cart = new Cart();
			System.out.println("----------------------"+order.getCustomer().getCustomerId());
//			orderServiceInterface.deleteCart(order.getCustomer().getCustomerId());
			orderServiceInterface.deleteCart(order.getCustomer().getCustomerId());
			
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
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/cancel-order")
	public OrderStatus cancelOrder(@RequestParam("orderId") int id) {
		Order o = orderServiceInterface.deleteOrder(id);
		//return ci.getCartItemId();
		OrderStatus status = new OrderStatus();
		status.setStatus(true);
		status.setMessage("Order cancelled successfully!");
		//status.setRegisteredCartItemId(id);
		return status;
	}
}
