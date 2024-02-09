package com.example.grocerybooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.grocerybooking.model.ApiResponse;
import com.example.grocerybooking.model.GroceryItem;

import com.example.grocerybooking.service.GroceryItemService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	 @Autowired
	    private GroceryItemService groceryItemService;

		
	 @GetMapping("/grocery-items")
		public ResponseEntity<ApiResponse<List<GroceryItem>>> getAllGroceryItems() {

			try {
				List<GroceryItem> groceryItems = groceryItemService.getAllGroceryItems();
				return ResponseEntity.ok(ApiResponse.success(groceryItems));
			} catch (Exception ex) {

				ex.printStackTrace();

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(ApiResponse.error("An unexpected error occurred."));
			}

		}
	 
	 @GetMapping("/grocery-items/{id}")
		public ResponseEntity<ApiResponse<GroceryItem>> getGroceryItemById(@PathVariable Long id) {

			try {
				GroceryItem groceryItem = groceryItemService.getGroceryItemById(id);
				return ResponseEntity.ok(ApiResponse.success(groceryItem));
			} catch (Exception ex) {

				ex.printStackTrace();

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(ApiResponse.error("An unexpected error occurred."));
			}
		}
	 
	 
	 @PostMapping("/grocery-items")
	    public ResponseEntity<ApiResponse<GroceryItem>> addGroceryItem(@RequestBody GroceryItem groceryItemBody) {
	       
	      
				GroceryItem groceryItem = groceryItemService.saveGroceryItem(groceryItemBody);
				return ResponseEntity.ok(ApiResponse.success(groceryItem));
		

				
			
	    }
	 
	 
	 @PutMapping("/grocery-items/{id}")
	    public ResponseEntity<String> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem updatedGroceryItem) {
	       
	        
	        try {
	        	 groceryItemService.updateGroceryItem(id, updatedGroceryItem);
	        	   return ResponseEntity.ok("Success");
			} catch (Exception ex) {

				ex.printStackTrace();

				 return ResponseEntity.ok("Failure");
			}
	        
	    }
	 
	 @DeleteMapping("/grocery-items/{id}")
	    public  ResponseEntity<String> deleteGroceryItem(@PathVariable Long id) {
	       
	        
	        try {
	        	 groceryItemService.deleteGroceryItem(id);
	        	   return ResponseEntity.ok("Success");
			} catch (Exception ex) {

				ex.printStackTrace();

				 return ResponseEntity.ok("Failure");
			}
	    }
	 
	
	
	
}
