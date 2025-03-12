package com.cg.backend_doc_checker.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

//JwtTokenFilter 
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, MalformedJwtException {
		
//		1. getToken
	 // Get authorization header and validate
        final String requestToken = request.getHeader("Authorization");
        System.err.println("!!!!!!!!!!!!!!!requestToken - "+requestToken);
        String username = null;
        String token = null;
        if (requestToken != null && request != null && !requestToken.equals("") && requestToken.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//        	  final String token = header.split(" ")[1].trim();
        	   token = requestToken.substring(7);
        	   try {
        		   username = this.jwtTokenHelper.getUsernameFromToken(token);
        	   }catch(IllegalArgumentException ex) {
        		   System.out.println("Unable to get Jwt token");
        	   }catch(ExpiredJwtException ex) {
        		   System.out.println("Jwt token has expired");
        	   }catch(MalformedJwtException ex) {
        		   System.out.println("Invalid Jwt token");
        	   }
        	   
        } else {
        	System.out.println("Jwt token does not begin with Bearer...");
        }
        
        
        
        //once we get the token, now validate the token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        	UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        	if(this.jwtTokenHelper.validateToken(token, userDetails)) {
        		//Shahi chal raha hai
        		//Authentication karna
        		
        		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		
        		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        		
        	}else {
            	System.out.println("Invalid jwt token");
            }
        }else {
        	System.out.println("Username null or context is not null.");
        }
        	
        filterChain.doFilter(request, response);
	}
	// ----------

}
