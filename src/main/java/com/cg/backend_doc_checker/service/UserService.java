package com.cg.backend_doc_checker.service;

import com.cg.backend_doc_checker.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

	void authenticate(String userName, String password);
	UserDetails loadUserByUsername(String userName);
	UserDto getUserIdByUsername(String username);
	UserDto createUser(UserDto userDto);
	List<UserDto> getAllUser();
	UserDto updateUser(UserDto userDto, Integer userId);
	//--------------------------------------
	//--------------------------------------


	UserDto getUserById(Integer userId);

	void deleteUser(Integer userId);



	String registerUser(RegisterRequest request);

	String loginUser(LoginRequest request);

	String updateProfile(Long userId, ProfileUpdateRequest request);



	String generateToken(UserDetails userDetails);

	UserDto forgotPasswordTest2(ForgotPasswordDto forgotPasswordDto);

//    String changePassword(PasswordChangeRequest request);
}
