package com.example.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.grocerybooking.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
