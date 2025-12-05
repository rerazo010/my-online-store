package com.mystore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.mystore.dto.OrderDTO;
import com.mystore.entity.Order;
import com.mystore.entity.OrderDetail;
import com.mystore.repository.OrderRepository;
import com.mystore.service.OrderService;
import com.mystore.service.UserService;
import com.mystore.util.SuccessResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserService userService;

	@Override
	public ResponseEntity<SuccessResponse> createOrder(OrderDTO orderDTO) {

		User userDetails = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Long userId = userService.getUserIdByUsername(userDetails.getUsername());
		
		Order order = new Order();
		order.setUserId(userId);
		order.setTotalAmount(orderDTO.getTotalAmount());
		order.setStatus("CREATED");
		order.setCreateDate(new Date());

		List<OrderDetail> details = new ArrayList<>();
		orderDTO.getDetails().forEach(detailDTO -> {
			OrderDetail detail = new OrderDetail();
			detail.setProductId(detailDTO.getProductId());
			detail.setPrice(detailDTO.getPrice());
			detail.setOrder(order);
			details.add(detail);
		});

		order.setDetails(details);

		return ResponseEntity.ok(new SuccessResponse("Order created successfully", orderRepository.save(order)));
	}

	@Override
	public ResponseEntity<SuccessResponse> getOrdersByUser(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		User userDetails = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Page<Order> orders = orderRepository.findByUserName(userDetails.getUsername(), pageable);
		return ResponseEntity.ok(new SuccessResponse("Orders retrieved successfully", orders));
	}

}
