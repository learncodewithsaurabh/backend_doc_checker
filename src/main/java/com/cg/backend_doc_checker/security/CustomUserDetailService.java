package com.cg.backend_doc_checker.security;

import com.cg.backend_doc_checker.exception.ResourceNotFoundException;
import com.cg.backend_doc_checker.model.User;
import com.cg.backend_doc_checker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// loading user from database by username
		User user = this.userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email :" + email, 0));
		return user;
	}

}
