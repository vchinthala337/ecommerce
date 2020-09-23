package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jpa.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
