package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFound.class)
	public ErrorMessage employeeNotFoundHandler(Exception ex) {
		LocalDateTime localDateTime= LocalDateTime.now();
		
		ErrorMessage errorMessage= new ErrorMessage();
		errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setTimestamp(localDateTime);
		
		return errorMessage;
	}

	@ExceptionHandler(Exception.class)
	public ErrorMessage globalExceptionHandler(Exception ex) {
		LocalDateTime localDateTime= LocalDateTime.now();
		
		ErrorMessage errorMessage= new ErrorMessage();
		errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setTimestamp(localDateTime);
		
		return errorMessage;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ErrorMessage globalExceptionHandlerRuntime(Exception ex) {
		LocalDateTime localDateTime= LocalDateTime.now();
		
		ErrorMessage errorMessage= new ErrorMessage();
		errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setTimestamp(localDateTime);
		
		return errorMessage;
	}
}
