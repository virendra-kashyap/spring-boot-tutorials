package com.vulnerabilities.spring.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserAppExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		final Map<String, String> errorDetails = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(err -> errorDetails.put(err.getField(), err.getDefaultMessage()));
		return errorDetails;
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Map<String, String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		final Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("errorMessage", ex.toString());
		return errorDetails;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleException(Exception ex) {
		final Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("errorMessage", ex.toString());
		return errorDetails;
	}

}
