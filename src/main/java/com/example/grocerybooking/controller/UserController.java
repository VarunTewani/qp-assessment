package com.example.grocerybooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.grocerybooking.model.ApiResponse;
import com.example.grocerybooking.model.GroceryItem;
import com.example.grocerybooking.model.Order;
import com.example.grocerybooking.model.OrderItem;
import com.example.grocerybooking.model.OrderRequestDTO;
import com.example.grocerybooking.service.OrderService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/available-grocery-items")
	public ResponseEntity<ApiResponse<List<GroceryItem>>> getAllGroceryItems() {
		try {
			List<GroceryItem> groceryItems = orderService.getAllGroceryItems();
			return ResponseEntity.ok(ApiResponse.success(groceryItems));
		} catch (Exception ex) {

			ex.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ApiResponse.error("An unexpected error occurred."));
		}
	}

	@PostMapping("/orderPlace")
	public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDTO orderRequest) {


			orderService.placeOrder(orderRequest);
			return new ResponseEntity<>(HttpStatus.CREATED);
		

	}
}
