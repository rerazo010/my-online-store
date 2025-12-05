package com.user_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUsers {

	@GetMapping("/test")
	public String test() {
		return "Hello, security was implemented succesfull!";
	}

}
