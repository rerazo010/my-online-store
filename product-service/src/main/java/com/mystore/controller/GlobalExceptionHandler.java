package com.mystore.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mystore.dto.ResponseDTO;

import io.swagger.v3.oas.annotations.Hidden;

//import config.ResourceNotFoundException;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String,String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String,String> error = Map.of(
            "field", ex.getName(),
            "message", "The '" + ex.getName() + "'  parameter must be a number"
        );
        return ResponseEntity.badRequest().body(error);
    }
    
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ResponseDTO> handleNotFound(ResourceNotFoundException ex) {
//        ResponseDTO body = new ResponseDTO(
//            ex.getMessage(),
//            ex.getMessage(),
//            null
//        );
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception ex) {
        ResponseDTO body = new ResponseDTO(
            "Internal Server Error",
            ex.getMessage(),
            null
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidExc
//    		eption ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors()
//          .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//        return errors;
//    }
    
}


