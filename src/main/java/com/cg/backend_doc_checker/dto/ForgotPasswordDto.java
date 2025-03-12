package com.cg.backend_doc_checker.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordDto {
	
	@NotEmpty(message = "Username should not be empty.")
	private String username;
	
	@NotEmpty(message = "Password should not be empty.")
	@Size(min = 8, message = "Password should be within length limit")
	private String newPassword;

}
