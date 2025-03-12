package com.cg.backend_doc_checker.controller;

import java.util.List;

import com.cg.backend_doc_checker.dto.JwtAuthRequest;
import com.cg.backend_doc_checker.dto.JwtAuthResponse;
import com.cg.backend_doc_checker.security.JwtTokenHelper;
import jakarta.validation.Valid;

import com.cg.backend_doc_checker.dto.ApiResponse;
import com.cg.backend_doc_checker.dto.UserDto;
import com.cg.backend_doc_checker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://localhost:3000")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
    UserController(){
        System.err.println("In UserController!!!");
    }
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/get_token")
    public ResponseEntity<String> getToken(@RequestBody JwtAuthRequest request) throws Exception {
        System.err.println("In UserController -> getToken!!");
        String generatedToken = null;
        if (request.getEmail() != null && request.getPassword() != null) {
            this.userService.authenticate(request.getEmail(), request.getPassword());
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
            generatedToken = this.jwtTokenHelper.generateToken(userDetails);
        }
        return new ResponseEntity<>(generatedToken, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) throws Exception {
        System.err.println("In UserController -> login!!");
        String userName = request.getEmail();
        userService.authenticate(userName, request.getPassword());
        //Get the details response of user
        UserDetails userDetails = userService.loadUserByUsername(userName);
        String generatedToken = userService.generateToken(userDetails);
        //Get customize response of user
        UserDto userDto = userService.getUserIdByUsername(userName);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(generatedToken);
//		jwtAuthResponse.setUserDetails(userDto);
        jwtAuthResponse.setUser(userDto);
        System.err.println("Login successfully!!!!!!!!!!!");
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


    //	register new user Api
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
    }
     @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId) {
        UserDto updateUser = this.userService.updateUser(userDto, uId);
        return ResponseEntity.ok(updateUser);
    }

    //	@PreAuthorize("hasRole('NORMAL')")
    @DeleteMapping("deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);
//			return ResponseEntity.ok(Map.of("message","User Deleted Successfully!"));
//			return new ResponseEntity(Map.of("message","User Deleted Successfully!"),HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully!", true), HttpStatus.OK);
    }

//	---------------------------------------------------------------------------------------------------------------------

    // API to handle forgot password requests
//    @PostMapping("/forgot-password")
//    public ResponseEntity<UserResponse> forgotPass(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {
//
//        UserDto userDto = this.userService.forgotPasswordTest2(forgotPasswordDto);
//        return new ResponseEntity<UserResponse>(new UserResponse(userDto, "Password reset successfully.", true),
//                HttpStatus.OK);
//    }




    //  Admin-Access only
//	DELETE-delete user





    //		GET-user-Single

}