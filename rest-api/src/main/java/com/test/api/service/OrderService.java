package com.test.api.service;

import org.springframework.stereotype.Service;

import com.test.api.dto.OrderDTO;
import com.test.api.feign.UserServiceFeign;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderService {

	private UserServiceFeign userServiceFeign;

	public OrderDTO getOrderById(Long orderId) {
		return null;
	}

	public OrderDTO create(OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderDTO cancelOrder(Long orderId, OrderDTO cancelOrder) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
