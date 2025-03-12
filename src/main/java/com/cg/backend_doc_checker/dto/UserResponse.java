package com.cg.backend_doc_checker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private UserDto userDto;
	private String message;
	private boolean isSuccess = false;

}
