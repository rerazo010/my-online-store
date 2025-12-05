package com.mystore.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mystore.UserDTO;
import com.mystore.entity.Role;
import com.mystore.entity.User;
import com.mystore.enumeration.ERole;
import com.mystore.repository.RoleRepository;
import com.mystore.repository.UserRepository;
import com.mystore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<String>  createUser(UserDTO userDTO) {
		//validate if user exist 
		if(userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
			        "Usuario ya existe: ".concat(userDTO.getUsername()));
			
		}
		
		
		Set<Role> roles = userDTO.getRoles().stream()
				.map(roleName -> roleRepository.findByName(ERole.valueOf(roleName)).get())
				.collect(Collectors.toSet());
		
		User user = User.builder()
				.username(userDTO.getUsername())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.email(userDTO.getEmail())
				.nombres(userDTO.getNombres())
				.apellidos(userDTO.getApellidos())
				.roles(roles)
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");

	}

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		return userRepository.findAll() != null ? ResponseEntity.ok(userRepository.findAll())
				: ResponseEntity.noContent().build();
	}

}
