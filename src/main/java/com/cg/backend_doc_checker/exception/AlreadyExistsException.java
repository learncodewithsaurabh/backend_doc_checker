package com.cg.backend_doc_checker.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public AlreadyExistsException() {
		super("Value already exist in database.");
	}
	
	public AlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

}
