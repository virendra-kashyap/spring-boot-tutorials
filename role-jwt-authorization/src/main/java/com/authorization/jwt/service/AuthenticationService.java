package com.authorization.jwt.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authorization.jwt.dto.TokenDTO;
import com.authorization.jwt.dto.UserDTO;
import com.authorization.jwt.repository.TokenRepository;
import com.authorization.jwt.repository.UserRepository;
import com.authorization.jwt.response.AuthenticationResponse;

@Service
public class AuthenticationService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	private final TokenRepository tokenRepository;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,
			TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.tokenRepository = tokenRepository;
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationResponse register(UserDTO request) {

		// check if user already exist. if exist than authenticate the user
		if (repository.findByUsername(request.getUsername()).isPresent()) {
			return new AuthenticationResponse(null, "User already exist");
		}

		UserDTO user = new UserDTO();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setRole(request.getRole());

		user = repository.save(user);

		String jwt = jwtService.generateToken(user);

		saveUserToken(jwt, user);

		return new AuthenticationResponse(jwt, "User registration was successful");

	}

	public AuthenticationResponse authenticate(UserDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		UserDTO user = repository.findByUsername(request.getUsername()).orElseThrow();
		String jwt = jwtService.generateToken(user);

		revokeAllTokenByUser(user);
		saveUserToken(jwt, user);

		return new AuthenticationResponse(jwt, "User login was successful");

	}

	private void revokeAllTokenByUser(UserDTO user) {
		List<TokenDTO> validTokens = tokenRepository.findAllTokensByUser(user.getId());
		if (validTokens.isEmpty()) {
			return;
		}

		validTokens.forEach(t -> {
			t.setLoggedOut(true);
		});

		tokenRepository.saveAll(validTokens);
	}

	private void saveUserToken(String jwt, UserDTO user) {
		TokenDTO token = new TokenDTO();
		token.setToken(jwt);
		token.setLoggedOut(false);
		token.setUser(user);
		tokenRepository.save(token);
	}

}
