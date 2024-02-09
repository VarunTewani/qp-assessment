package com.example.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.grocerybooking.model.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>{

	
	  @Query(value = "SELECT COUNT(*) FROM grocery_item WHERE name = :inventoryName", nativeQuery = true)
	    Long countByInventoryName(@Param("inventoryName") String inventoryName);
}
