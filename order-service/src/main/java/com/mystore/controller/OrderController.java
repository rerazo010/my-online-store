package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.dto.OrderDTO;
import com.mystore.service.OrderService;
import com.mystore.util.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<SuccessResponse> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
		return orderService.createOrder(orderDTO);
	}

	@GetMapping("/client/my-orders")
	public ResponseEntity<SuccessResponse> getClientOrders(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return orderService.getOrdersByUser(page, size);
	}

}
