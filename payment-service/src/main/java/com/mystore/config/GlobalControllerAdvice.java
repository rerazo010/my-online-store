package com.mystore.config;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mystore.util.ErrorResponse;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;

@Hidden
@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		Map<String, String> error = Map.of("field", ex.getName(), "message",
				"The '" + ex.getName() + "' parameter must be a number");
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errores = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errores.put(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errores);
	}

	@ExceptionHandler(PayOrderException.class)
	public ResponseEntity<ErrorResponse> handlePayOrderException(PayOrderException ex, HttpServletRequest request) {
		ErrorResponse err = new ErrorResponse();
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError(HttpStatus.CONFLICT.getReasonPhrase());
		err.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException ex, HttpServletRequest request) {
		ErrorResponse err = new ErrorResponse();
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		err.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
