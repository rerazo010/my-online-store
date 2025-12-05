package com.mystore.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mystore.UserDTO;
import com.mystore.entity.User;

public interface UserService {

	public ResponseEntity<String> createUser(UserDTO user);
	public ResponseEntity<List<User>> getAllUsers();

}
