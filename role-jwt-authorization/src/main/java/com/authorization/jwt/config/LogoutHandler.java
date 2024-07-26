package com.authorization.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import com.authorization.jwt.dto.TokenDTO;
import com.authorization.jwt.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}

		String token = authHeader.substring(7);
		TokenDTO storedToken = tokenRepository.findByToken(token).orElse(null);

		if (storedToken != null) {
			storedToken.setLoggedOut(true);
			tokenRepository.save(storedToken);
		}

	}

}
