package com.mystore.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mystore.security.filter.JwtAuthenticationFilter;
import com.mystore.security.filter.JwtAuthorizationFilter;
import com.mystore.security.jwt.JwtUtils;
import com.mystore.service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailServiceImpl userDetailService;

	@Autowired
	JwtAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
			throws Exception {

		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);

		return httpSecurity.csrf(config -> config.disable())
				.authorizeHttpRequests(aut -> {
					aut.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
					aut.requestMatchers("/error").permitAll();
					aut.anyRequest().authenticated();
				}).sessionManagement(session -> {
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
			throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailService)
				.passwordEncoder(passwordEncoder).and().build();

	}

}
