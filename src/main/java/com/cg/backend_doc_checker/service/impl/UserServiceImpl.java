package com.cg.backend_doc_checker.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cg.backend_doc_checker.config.AppConstants;
import com.cg.backend_doc_checker.dto.*;
import com.cg.backend_doc_checker.exception.ApiException;
import com.cg.backend_doc_checker.exception.ResourceNotFoundException;
import com.cg.backend_doc_checker.model.Role;
import com.cg.backend_doc_checker.model.User;
import com.cg.backend_doc_checker.repository.RoleRepository;
import com.cg.backend_doc_checker.repository.UserRepository;
import com.cg.backend_doc_checker.security.JwtTokenHelper;
import com.cg.backend_doc_checker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private ModelMapper modelMapper;
//	ModelMapper is used for map one object to another object

    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
        return userDto;
    }

    public void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException ex) {
            System.out.println("Invalid user email or password!");
            throw new ApiException("Invalid username or password!");
        }
    }

    public UserDetails loadUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    public String generateToken(UserDetails userDetails) {
        return jwtTokenHelper.generateToken(userDetails);
    }

    @Override
    public UserDto forgotPasswordTest2(ForgotPasswordDto forgotPasswordDto) {

        User user = this.userRepo.findByEmail(forgotPasswordDto.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("User", "Username: " + forgotPasswordDto.getUsername(), 0));

        if (user != null) {
            user.setPassword(this.passwordEncoder.encode(forgotPasswordDto.getNewPassword()));
        }

        User savedUser = this.userRepo.save(user);

        UserDto userDto = this.modelMapper.map(savedUser, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        System.out.println("In UserController -> createUser!!!");
        User user = this.modelMapper.map(userDto, User.class);
        //encoded the password
        System.out.println("UserServiceImpl -> User password = " + user.getPassword());
        System.out.println("UserServiceImpl -> User Encoded password = " + this.passwordEncoder.encode(user.getPassword()));
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Optional<Role> role = this.roleRepo.findById(AppConstants.NORMAL_USER_ID);
        System.err.println("role.get()+role.get() ==="+role.get());

        user.getRoles().add(role.get());
        User newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser, UserDto.class);
    }
    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
        return userDtos;
    }
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
//        System.out.println("userDto.getUsername()-"+userDto.getUsername());
//        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = this.userRepo.save(user);
        System.out.println("updatedUser.getUsername()-"+updatedUser.getUsername());
        return this.modelMapper.map(updatedUser, UserDto.class);
    }


//	----------------------------------------
//	----------------------------------------


    @Override
    public String loginUser(LoginRequest request) {
        return null;
    }

    @Override
    public String updateProfile(Long userId, ProfileUpdateRequest request) {
        return null;
    }




    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.userToUserDto(user);
    }



    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }

    @Override
    public String registerUser(RegisterRequest request) {
        return null;
    }


    @Override
    public UserDto getUserIdByUsername(String username) {
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", 0));
        return this.userToUserDto(user);
    }
}