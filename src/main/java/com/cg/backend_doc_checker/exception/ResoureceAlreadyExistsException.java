package com.cg.backend_doc_checker.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResoureceAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourseName;
	private String fieldName;
	private String fieldValue;
	
	public ResoureceAlreadyExistsException(String resourseName, String fieldName, String fieldValue) {
		super(String.format("%s is already exists with %s : %s", resourseName, fieldName, fieldValue));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
