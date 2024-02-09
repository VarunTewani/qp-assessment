package com.example.grocerybooking.model;

public class ApiResponse<T> {

	private boolean success;

	private T data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> ApiResponse<T> success(T data) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(true);
		response.setData(data);
		return response;
	}

	public static <T> ApiResponse<T> error(String message) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(false);
		
		return response;
	}
}
