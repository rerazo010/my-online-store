package com.user_management.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.user_management.dto.UserDTO;
import com.user_management.dto.UserStatusDTO;
import com.user_management.entity.Role;
import com.user_management.entity.User;
import com.user_management.enumeration.ERole;
import com.user_management.repository.RoleRepository;
import com.user_management.repository.UserRepository;
import com.user_management.service.UserService;
import com.user_management.util.SuccessResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<SuccessResponse> createUser(UserDTO userDTO) {
		// validate if user exist
		if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User already exists: ".concat(userDTO.getUsername()));

		}

		Set<Role> roles = userDTO.getRoles().stream()
				.map(roleName -> roleRepository.findByName(ERole.valueOf(roleName)).get()).collect(Collectors.toSet());

		User user = User.builder().username(userDTO.getUsername())
				.password(passwordEncoder.encode(userDTO.getPassword())).email(userDTO.getEmail())
				.nombres(userDTO.getNombres()).apellidos(userDTO.getApellidos()).roles(roles).enable(true).build();

		userRepository.save(user);

		SuccessResponse response = new SuccessResponse("User created successfully", user.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		return userRepository.findAll() != null ? ResponseEntity.ok(userRepository.findAll())
				: ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<SuccessResponse> updateStatus(UserStatusDTO userDTO) {
		// validate if user exist
		User user = userRepository.findById(userDTO.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"User does not exist with ID:".concat(userDTO.getId().toString())));

		user.setEnable(userDTO.getEnable());
		userRepository.save(user);
		SuccessResponse response = new SuccessResponse("User status updated successfully", user.getId());
		return ResponseEntity.ok(response);
	}

}
