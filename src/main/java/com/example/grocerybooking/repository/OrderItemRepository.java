package com.example.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.grocerybooking.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>  {

}
