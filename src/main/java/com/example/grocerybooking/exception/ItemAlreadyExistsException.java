package com.example.grocerybooking.exception;

public class ItemAlreadyExistsException extends RuntimeException {

	
	public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
