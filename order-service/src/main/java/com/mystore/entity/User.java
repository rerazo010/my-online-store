package com.mystore.entity;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Column(name = "activo")
	@NotNull
	private Boolean enable;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE }, targetEntity = Role.class)
	@JoinTable( // intermediate table for roles
			name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

}
