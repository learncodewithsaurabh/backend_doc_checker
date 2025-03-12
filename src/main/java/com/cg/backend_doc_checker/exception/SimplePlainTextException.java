package com.cg.backend_doc_checker.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePlainTextException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public SimplePlainTextException() {
		super("SimplePlainTextException occured due to some event.");
	}

	public SimplePlainTextException(String message) {
		super(message);
		this.message = message;
	}
}
