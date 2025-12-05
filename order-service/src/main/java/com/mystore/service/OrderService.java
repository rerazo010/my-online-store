package com.mystore.service;

import org.springframework.http.ResponseEntity;

import com.mystore.dto.OrderDTO;
import com.mystore.util.SuccessResponse;


public interface OrderService {
	
	public ResponseEntity<SuccessResponse> createOrder(OrderDTO orderDTO);
	public ResponseEntity<SuccessResponse> getOrdersByUser(int page, int size);

}
