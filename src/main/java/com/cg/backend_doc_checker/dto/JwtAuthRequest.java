package com.cg.backend_doc_checker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {
 private String email;
 private String password;
}
