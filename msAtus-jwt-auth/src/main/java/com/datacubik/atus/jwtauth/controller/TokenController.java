package com.datacubik.atus.jwtauth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datacubik.atus.jwtauth.exception.CustomException;
import com.datacubik.atus.jwtauth.service.impl.UserService;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	UserService userService;
	
	@Value("${basicAuthentication.clientId}")
	private String clientId;
	
	@Value("${basicAuthentication.clientSecret}")
	private String clientSecret;

	@PostMapping("/login")
	public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
		if(!isBasicAuthenticated(request.getHeader("Authorization"))) {
			throw new CustomException("Invalid basic authentication supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return userService.signin(username, password);
	}

	@GetMapping("/refresh")
	public String refresh(HttpServletRequest request) {
		if(!isBasicAuthenticated(request.getHeader("Authorization"))) {
			throw new CustomException("Invalid basic authentication supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return userService.refresh(request.getRemoteUser());
	}
	
	private boolean isBasicAuthenticated(String authorization) {
		if (authorization != null && authorization.startsWith("Basic")) 
	    {
			String credentials = authorization.substring("Basic".length()).trim();
	        byte[] decoded = DatatypeConverter.parseBase64Binary(credentials);
	        String decodedString = new String(decoded);
	        String[] actualCredentials = decodedString.split(":");
	        String id = actualCredentials[0];
	        String secret = actualCredentials[1];
	        
	        if(clientId.equals(id) && clientSecret.equals(secret))
	        	return true;
	    }
		return false;
	}
}
