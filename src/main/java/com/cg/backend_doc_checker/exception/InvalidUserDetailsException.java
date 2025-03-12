package com.cg.backend_doc_checker.exception;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class InvalidUserDetailsException extends Exception {
	
	private String message;

	public InvalidUserDetailsException() {
		super("User entered invalid username or password !!");
	}

	public InvalidUserDetailsException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
