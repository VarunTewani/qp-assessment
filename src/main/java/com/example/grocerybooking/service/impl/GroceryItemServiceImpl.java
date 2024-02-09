package com.example.grocerybooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.grocerybooking.exception.ItemAlreadyExistsException;
import com.example.grocerybooking.model.GroceryItem;
import com.example.grocerybooking.repository.GroceryItemRepository;
import com.example.grocerybooking.service.GroceryItemService;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

	
	  @Autowired
	    private GroceryItemRepository groceryItemRepository;
	
	@Override
	public List<GroceryItem> getAllGroceryItems() {
		return groceryItemRepository.findAll();
	}

	@Override
	public GroceryItem saveGroceryItem(GroceryItem groceryItem) {
		
		
		 Long countItemExists = groceryItemRepository.countByInventoryName(groceryItem.getName());

		
		if(countItemExists >0) {
			
			throw new ItemAlreadyExistsException("Item with name '" + groceryItem.getName() + "' already exists");
		}
		
		 
		return groceryItemRepository.save(groceryItem);
	
	}

	@Override
	public void deleteGroceryItem(Long id) {
		groceryItemRepository.deleteById(id);
		
	}

	@Override
	public GroceryItem getGroceryItemById(Long id) {
		return groceryItemRepository.findById(id).orElse(null);
	}

	@Override
	public void updateGroceryItem(Long id, GroceryItem updatedGroceryItem) {
		GroceryItem existingItem = groceryItemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setName(updatedGroceryItem.getName());
            existingItem.setPrice(updatedGroceryItem.getPrice());
            existingItem.setInventory(updatedGroceryItem.getInventory());
            groceryItemRepository.save(existingItem);
        }
		
	}

}
