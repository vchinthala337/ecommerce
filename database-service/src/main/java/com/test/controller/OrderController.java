/**
 * 
 */
package com.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.jpa.Order;
import com.test.repository.OrderRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController("/order")
public class OrderController {

	private OrderRepo orderRepo;

	@GetMapping("/{oderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
		return ResponseEntity.ok(orderRepo.getOne(orderId));
	}

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody Order order) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderRepo.save(order));
	}
	
	@PostMapping("/bulk")
	public ResponseEntity<List<Order>> createBulk(@RequestBody List<Order> orders) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderRepo.saveAll(orders));
	}

	@PutMapping("cancel/{oderId}")
	public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId, @RequestBody Order cancelOrder) {
		return orderRepo.findById(orderId).map(order -> {
			order.setOrderStatus("cancel");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderRepo.save(order));
		}).orElseGet(() -> {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(cancelOrder);
		});
	}
}
