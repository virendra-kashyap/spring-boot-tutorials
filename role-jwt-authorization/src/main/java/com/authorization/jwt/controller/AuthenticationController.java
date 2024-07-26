package com.authorization.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.jwt.dto.UserDTO;
import com.authorization.jwt.response.AuthenticationResponse;
import com.authorization.jwt.service.AuthenticationService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;

	@PostMapping("/auth/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO request) {
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/auth/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody UserDTO request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
