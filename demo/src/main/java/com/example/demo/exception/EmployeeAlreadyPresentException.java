package com.example.demo.exception;

public class EmployeeAlreadyPresentException extends Exception{
	
	public EmployeeAlreadyPresentException(String message) {
		super(message);
	}

}
