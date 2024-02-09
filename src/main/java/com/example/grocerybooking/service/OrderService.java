package com.example.grocerybooking.service;


import java.util.List;

import com.example.grocerybooking.model.GroceryItem;
import com.example.grocerybooking.model.Order;
import com.example.grocerybooking.model.OrderItem;
import com.example.grocerybooking.model.OrderRequestDTO;

public interface OrderService {

	 List<GroceryItem> getAllGroceryItems();
	
	 void placeOrder(OrderRequestDTO orderRequest);
}
