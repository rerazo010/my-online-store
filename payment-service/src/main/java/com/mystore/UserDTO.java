package com.mystore;

import java.util.Set;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {

	private Long id;

	@NotNull
	private String nombres;
	
	@NotNull
	private String apellidos;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String username;
	
	@Column(name = "clave")
	@NotNull
	private String password;
	
	@NotNull
	private Set<String> roles;
	
}
