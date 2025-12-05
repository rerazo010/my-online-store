package com.user_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_management.dto.UserDTO;
import com.user_management.dto.UserStatusDTO;
import com.user_management.service.UserService;
import com.user_management.util.SuccessResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create")
	public ResponseEntity<SuccessResponse> createUser(@RequestBody @Validated UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
	
	@PatchMapping(value = "/status")
	public ResponseEntity<SuccessResponse> udpateUser(@RequestBody @Validated UserStatusDTO userStatusDTO) {
		return userService.updateStatus(userStatusDTO);
		
	}

}
