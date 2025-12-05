package com.mystore.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PayOrderException extends RuntimeException {

	public PayOrderException(Long orderId) {
		super("Order with id " + orderId + " had already been paid.");
	}

	public PayOrderException(String message) {
		super(message);
	}
}
