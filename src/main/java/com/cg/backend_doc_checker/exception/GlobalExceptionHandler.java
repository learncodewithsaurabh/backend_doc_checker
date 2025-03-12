package com.cg.backend_doc_checker.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import com.cg.backend_doc_checker.dto.ApiResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		Map<String, String> resp = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<Map<String, String>> handlePropertyReferenceException(PropertyReferenceException ex) {
		Map<String, String> resp = new HashMap<String, String>();
		String message = ex.getMessage();
		resp.put("Server's responce", message);
		resp.put("Developers' response", "No such a field is currently present in database.");
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidUserDetailsException.class)
	public ResponseEntity<ApiResponse> handleInvalidUserDetailsException(InvalidUserDetailsException ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<ApiResponse> handleAlreadyExistsException(AlreadyExistsException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(SimplePlainTextException.class)
	public ResponseEntity<Map<String, String>> handleSimplePlainTextException(SimplePlainTextException ex) {

		Map<String, String> resp = new HashMap<String, String>();
		String message = ex.getMessage();
		resp.put("message", message);

		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResoureceAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleResoureceAlreadyExistsException(
			ResoureceAlreadyExistsException ex) {

		Map<String, String> resp = new HashMap<String, String>();
		String message = ex.getMessage();
		resp.put("message", message);

		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponse> handleSQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

}
