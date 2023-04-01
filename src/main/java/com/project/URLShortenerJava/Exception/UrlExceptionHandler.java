package com.project.URLShortenerJava.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class UrlExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidLongUrlException.class)
	protected final ResponseEntity<Map<String, String>> handleInvalidLongUrlException(InvalidLongUrlException ex) {
		Map<String,String> errorDetails = new HashMap<>();
		errorDetails.put("date",LocalDateTime.now().toString());
		errorDetails.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	@ExceptionHandler(ShortUrlNotFoundException.class)
	protected final ResponseEntity<Map<String, String>> handleShortUrlNotFoundException(ShortUrlNotFoundException ex) {
		Map<String,String> errorDetails = new HashMap<>();
		errorDetails.put("date",LocalDateTime.now().toString());
		errorDetails.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(UrlAlreadyExistsException.class)
	protected final ResponseEntity<Map<String, String>> handleUrlAlreadyExistsException(UrlAlreadyExistsException ex) {
		Map<String,String> errorDetails = new HashMap<>();
		errorDetails.put("message",ex.getMessage());
		errorDetails.put("url",ex.getUrl());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorDetails);
	}
	
	@ExceptionHandler(NoDataExistsException.class)
	protected final ResponseEntity<Map<String, String>> handleNoDataExistsException(NoDataExistsException ex) {
		Map<String,String> errorDetails = new HashMap<>();
		errorDetails.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(InvalidDateException.class)
	protected final ResponseEntity<Map<String, String>> handleInvalidDateException(InvalidDateException ex) {
		Map<String,String> errorDetails = new HashMap<>();
		errorDetails.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	
}
