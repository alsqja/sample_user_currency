package com.sparta.currency_user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", ex.getStatusCode().value());
        errorDetails.put("error", ex.getStatusCode());
        errorDetails.put("message", ex.getReason());
        errorDetails.put("timestamp", System.currentTimeMillis());

        return new ResponseEntity<>(errorDetails, ex.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> err = new HashMap<>();
        err.put("status", HttpStatus.BAD_REQUEST.value());
        err.put("error", HttpStatus.BAD_REQUEST);
        err.put("message", Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        err.put("timestamp", System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
