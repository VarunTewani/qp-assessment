package com.example.grocerybooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<String> handleInsufficientInventoryException(InsufficientInventoryException ex) {
         String errorMessage = "Insufficient inventory: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(GroceryItemNotFoundException.class)
    public ResponseEntity<String> GroceryItemNotFoundException(GroceryItemNotFoundException ex) {
        String errorMessage = "Grocery item not found: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<String> ItemAlreadyExistsException(ItemAlreadyExistsException ex) {
        String errorMessage = "Grocery item already present: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> UserNotFoundException(UserNotFoundException ex) {
        String errorMessage = "User not present in system " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


}
