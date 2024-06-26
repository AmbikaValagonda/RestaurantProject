package com.restaurantmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantmanagement.entity.CustomerOrder;
import com.restaurantmanagement.entity.CustomerOrder.OrderStatus;
import com.restaurantmanagement.entity.Reservation;
import com.restaurantmanagement.entity.Reservation.ReservationStatus;
import com.restaurantmanagement.repos.OrderRepository;
import com.restaurantmanagement.service.IOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@GetMapping("/get-orders")
	public List<CustomerOrder> getAllorders() {
		logger.info("Request for getting all orders ... ");
		return orderService.getAllOrders();
	}

	@PostMapping("/place-order")
	public CustomerOrder placeOrder(@RequestBody CustomerOrder order) {
		logger.info("Request for adding order ...");
		order.setStatus(OrderStatus.PENDING);
	    return orderService.placeOrder(order);
	}

		
	@GetMapping("/{id}")
	public CustomerOrder getOrderById(@PathVariable Long id) {
	    logger.info("Request for Order with id: "+ id);
	    return orderService.getOrderById(id);
	} 
	
	@DeleteMapping("/{id}")
	public void deleteOrderById(@PathVariable Long id) {
	    logger.info("Request for delete order with id: "+ id);
	    orderService.deleteOrderById(id);
	}
    
	@PutMapping("/{id}")
	public CustomerOrder updateOrderById(@PathVariable Long id, @RequestBody CustomerOrder updatedOrder) {
		logger.info("Request for order update");
		if(!id.equals(updatedOrder.getOrderID())) {
			throw new IllegalArgumentException("Path variable ID and request body ID do not match");
		}
		return orderService.updateOrder(updatedOrder);
	}
	
	

}
