package com.user_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.user_management.dto.UserDTO;
import com.user_management.dto.UserStatusDTO;
import com.user_management.entity.User;
import com.user_management.util.SuccessResponse;

public interface UserService {

	public ResponseEntity<SuccessResponse> createUser(UserDTO user);
	public ResponseEntity<List<User>> getAllUsers();
	public ResponseEntity<SuccessResponse> updateStatus(UserStatusDTO user);

}
