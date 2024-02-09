package com.example.grocerybooking.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.grocerybooking.exception.GroceryItemNotFoundException;
import com.example.grocerybooking.exception.InsufficientInventoryException;
import com.example.grocerybooking.model.GroceryItem;
import com.example.grocerybooking.model.Order;
import com.example.grocerybooking.model.User;
import com.example.grocerybooking.model.OrderItem;
import com.example.grocerybooking.model.OrderItemDTO;
import com.example.grocerybooking.model.OrderRequestDTO;
import com.example.grocerybooking.repository.GroceryItemRepository;
import com.example.grocerybooking.repository.OrderItemRepository;
import com.example.grocerybooking.repository.OrderRepository;
import com.example.grocerybooking.repository.UserRepository;
import com.example.grocerybooking.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	GroceryItemRepository groceryItemRepository;

	@Autowired
	OrderRepository orderRepository;

     @Autowired
     OrderItemRepository orderItemRepository;
	
     @Autowired
	UserRepository userRepository;


    @Transactional
	@Override
    public void placeOrder(OrderRequestDTO orderRequest) {
       
		Order order = new Order();

		
        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new GroceryItemNotFoundException("User not present in system"));

      
        
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderRequest.getOrderItems()) {
			
        	
        	GroceryItem groceryItem = groceryItemRepository.findById(itemDTO.getGroceryItemId()).orElseThrow(() -> new GroceryItemNotFoundException("Grocery item not found"));
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setGroceryItem(groceryItem);
            orderItem.setQuantity(itemDTO.getQuantity());
			
			  if (groceryItem.getInventory() < itemDTO.getQuantity()) {  throw new
			   InsufficientInventoryException("Insufficient inventory for grocery item: " +
			  groceryItem.getName()); }
			 
            groceryItem.setInventory(groceryItem.getInventory() - itemDTO.getQuantity());

            orderItems.add(orderItem);
        }
        
      

        order.setOrderItems(orderItems);
        order.setOrderDate(Timestamp.from(Instant.now()));

        
        orderRepository.save(order);
        
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem);
        }
        
        
    }



	@Override
	public List<GroceryItem> getAllGroceryItems() {

		Optional<List<GroceryItem>> optionalGroceryItems = Optional.ofNullable(groceryItemRepository.findAll());

		List<GroceryItem> finalGroceryItemList = new ArrayList<GroceryItem>();

		if (optionalGroceryItems.isPresent()) {

			List<GroceryItem> lstGroceryItems = optionalGroceryItems.get();

			for (GroceryItem groceryItem : lstGroceryItems) {

				if (groceryItem.getInventory() > 0) {

					finalGroceryItemList.add(groceryItem);
				}
			}

			return finalGroceryItemList;
		} else {

			return Collections.emptyList();
		}

	}

	

}
