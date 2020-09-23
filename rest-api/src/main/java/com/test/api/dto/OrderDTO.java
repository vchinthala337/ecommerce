package com.test.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderDTO {

	private Long orderId;
	private String orderStatus;
	private Long customerId;
	private String orderItemName;
	private Integer orderItemQty;
	private Double orderSubtotal;
	private Double orderTax;
	private Double orderShippingCharges;
	private Double orderTotal;
	private String orderPaymentMethod;
	private Date orderPaymentDate;
	private Long orderPaymentConfirmationNumber;
	private String orderBillingAddressline1;
	private String orderBillingAddressline2;
	private String orderBillingCity;
	private String orderBillingState;
	private String orderBillingZip;
}
