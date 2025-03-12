package com.cg.backend_doc_checker.dto;

//import lombok.Data;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Date;
//
//@Data
//public class JwtAuthResponse {
//    private String token;
//    //adding user data
//    private UserDto user;
//
//    public JwtAuthResponse(String token, Date date, String s, UserDto map) {
//    }
//
//
//    //For the detail user response
////	private UserDetails userDetails;
//
////
//
//
//}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

    private String token;
    private Date tokenCreationDate;
    private String tokenValidity;
    private UserDto user;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

}
