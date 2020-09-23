package com.test.batch;

import org.springframework.batch.item.ItemProcessor;

import com.test.dto.OrderDTO;

public class OrderItemProcessor implements ItemProcessor<OrderDTO, OrderDTO> {

	@Override
	public OrderDTO process(final OrderDTO orderDTO) throws Exception {
		orderDTO.setOrderStatus("New");
		orderDTO.setOrderTotal(
				orderDTO.getOrderSubtotal() + orderDTO.getOrderTax() + orderDTO.getOrderShippingCharges());

		return orderDTO;
	}

}