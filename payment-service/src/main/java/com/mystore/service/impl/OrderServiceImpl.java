package com.mystore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.mystore.config.OrderNotFoundException;
import com.mystore.config.PayOrderException;
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
	public ResponseEntity<SuccessResponse> payOrder(Long orderId) {

		//identify logged in client
		User userDetails = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		Long userId = userService.getUserIdByUsername(userDetails.getUsername());
		
		
		Long foundRegister = orderRepository.findByOrderIdAndUserId(orderId,userId);
        if (foundRegister==0) {
            throw new OrderNotFoundException(orderId);
        }
        
        int updated = orderRepository.payOrder(orderId);
        if (updated == 0) {
            throw new PayOrderException(orderId);
        }
        
		return ResponseEntity.ok(new SuccessResponse("Order paid successfully","OrderId "+ orderId));
	}

}
