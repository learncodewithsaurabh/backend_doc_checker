package com.cg.backend_doc_checker.dto;

import jakarta.validation.constraints.NotBlank;

public class PasswordChangeRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;

    // Getters and setters
}
