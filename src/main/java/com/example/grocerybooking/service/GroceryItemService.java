package com.example.grocerybooking.service;

import java.util.List;

import com.example.grocerybooking.model.GroceryItem;

public interface GroceryItemService {
	
	 List<GroceryItem> getAllGroceryItems();
	 
	 GroceryItem saveGroceryItem(GroceryItem groceryItem);
	 
	  void deleteGroceryItem(Long id);
	  
	  GroceryItem getGroceryItemById(Long id);
	  
	  void updateGroceryItem(Long id, GroceryItem updatedGroceryItem);

}
