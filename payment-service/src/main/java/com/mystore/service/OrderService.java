package com.mystore.service;

import org.springframework.http.ResponseEntity;

import com.mystore.util.SuccessResponse;


public interface OrderService {
	
	public ResponseEntity<SuccessResponse> payOrder(Long orderId);

}
