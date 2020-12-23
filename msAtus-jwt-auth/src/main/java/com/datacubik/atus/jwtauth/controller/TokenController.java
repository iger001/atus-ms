package com.datacubik.atus.jwtauth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datacubik.atus.jwtauth.service.impl.UserService;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZXJhcmRvIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjA4MDE1NDQxLCJleHAiOjE2MDgwMTU3NDF9.nLhqQEShQu7nCfKuR2pJost_E5jlLCT_Es9eU4_w37U";
		return userService.signin(username, password);
	}

	@GetMapping("/refresh")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public String refresh(HttpServletRequest req) {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZXJhcmRvIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjA4MDE1NDQxLCJleHAiOjE2MDgwMTU3NDF9.nLhqQEShQu7nCfKuR2pJost_E5jlLCT_Es9eU4_w37U";
		return token;// return userService.refresh(req.getRemoteUser());
	}
}
