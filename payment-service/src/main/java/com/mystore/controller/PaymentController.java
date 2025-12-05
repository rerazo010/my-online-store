package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.service.OrderService;
import com.mystore.util.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<SuccessResponse> payOrder(@Valid @RequestParam Long orderId) {
		return orderService.payOrder(orderId);
	}
}
