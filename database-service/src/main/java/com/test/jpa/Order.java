package com.test.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long orderId;
	@Column(name="order_status")
	private String orderStatus;
	@Column(name="order_customer_id")
	private Long customerId;
	@Column(name="order_item_name")
	private String orderItemName;
	@Column(name="order_item_qty")
	private Integer orderItemQty;
	@Column(name="order_subtotal")
	private Double orderSubtotal;
	@Column(name="order_tax")
	private Double orderTax;
	@Column(name="order_shipping_charges")
	private Double orderShippingCharges;
	@Column(name="order_total")
	private Double orderTotal;
	@Column(name="order_payment_method")
	private String orderPaymentMethod;
	@Column(name="order_payment_date")
	private Date orderPaymentDate;
	@Column(name="order_payment_confirmation_number")
	private Long orderPaymentConfirmationNumber;
	@Column(name="order_billing_addressline1")
	private String orderBillingAddressline1;
	@Column(name="order_billing_addressline2")
	private String orderBillingAddressline2;
	@Column(name="order_billing_city")
	private String orderBillingCity;
	@Column(name="order_billing_state")
	private String orderBillingState;
	@Column(name="order_billing_zip")
	private String orderBillingZip;
}
