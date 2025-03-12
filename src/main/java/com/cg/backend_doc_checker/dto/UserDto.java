package com.cg.backend_doc_checker.dto;

import com.cg.backend_doc_checker.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer userId;
//    @Size(min = 4, message = "Username must be min 4 characters!!")
//    private String username;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Size(min = 3, max = 15, message = "Password must be min 3 and maximum 15 characters!!")
    private String password;
    Set<Role> userRoles = new HashSet<>();
    // getters and setters...
}