package com.test.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.api.dto.OrderDTO;

@FeignClient(name="db-service")
public interface UserServiceFeign {

	@GetMapping("/order/{orderId}")
	OrderDTO getOrderById(@PathVariable Long orderId);

	@PostMapping("/order")
	public OrderDTO create(@RequestBody OrderDTO order);

	@PutMapping("/order/cancel/{orderId}")
	public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId, @RequestBody OrderDTO cancelOrder);
}
