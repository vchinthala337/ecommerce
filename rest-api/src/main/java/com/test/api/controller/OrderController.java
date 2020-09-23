package com.test.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.dto.OrderDTO;
import com.test.api.service.OrderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

	private OrderService orderService;
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}

	@PostMapping
	public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO order) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(order));
	}

	@PutMapping("cancel/{orderId}")
	public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId, @RequestBody OrderDTO cancelOrder) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderService.cancelOrder(orderId, cancelOrder));
	}
}
