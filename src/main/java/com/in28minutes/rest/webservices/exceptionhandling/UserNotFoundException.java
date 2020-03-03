package com.in28minutes.rest.webservices.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -6805753427450119207L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
