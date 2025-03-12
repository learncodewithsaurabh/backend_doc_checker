package com.cg.backend_doc_checker.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private long roleId;
}

