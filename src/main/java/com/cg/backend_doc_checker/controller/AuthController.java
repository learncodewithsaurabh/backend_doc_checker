package com.cg.backend_doc_checker.controller;

import java.util.Date;


import com.cg.backend_doc_checker.dto.*;
import com.cg.backend_doc_checker.exception.InvalidUserDetailsException;
import com.cg.backend_doc_checker.model.User;
import com.cg.backend_doc_checker.security.JwtTokenHelper;
import com.cg.backend_doc_checker.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AuthController(){
        System.err.println("In AuthController!!!");
    }

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;



//	-----------------------------------------------------------------------------------------------------------------------------------

    // API to register new user : Security not applied to this API.
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto)
//            throws ResoureceAlreadyExistsException {
//
//        System.out.println(userDto);
//
//        UserDto registeredUser = this.userService.registerNewUser(userDto);
//
//        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
//    }

//	-----------------------------------------------------------------------------------------------------------------------------------

    // API to register new admin user : Security not applied to this API.
    @PostMapping("/register-admin")
    public ResponseEntity<UserDto> registerAdminUser(@Valid @RequestBody UserDto userDto) {

        System.out.println(userDto);

        UserDto registeredUser = this.userService.createUser(userDto);

        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
    }

//	-----------------------------------------------------------------------------------------------------------------------------------

    // API to handle forgot password requests
    @PostMapping("/forgot-password")
    public ResponseEntity<UserResponse> forgotPass(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {

        UserDto userDto = this.userService.forgotPasswordTest2(forgotPasswordDto);
        return new ResponseEntity<UserResponse>(new UserResponse(userDto, "Password reset successfully.", true),
                HttpStatus.OK);
    }

}
