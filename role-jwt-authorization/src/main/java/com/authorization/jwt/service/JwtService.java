package com.authorization.jwt.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.authorization.jwt.dto.UserDTO;
import com.authorization.jwt.repository.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String secretKey;
	private final TokenRepository tokenRepository;

	public JwtService(@Value("${jwt.secret-key}") String secretKey, TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
		this.secretKey = secretKey;
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public boolean isValid(String token, UserDetails user) {
		String username = extractUsername(token);

		boolean validToken = tokenRepository.findByToken(token).map(t -> !t.isLoggedOut()).orElse(false);

		return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
	}

	public String generateToken(UserDTO user) {
		String token = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)).signWith(getSigninKey())
				.compact();

		return token;
	}

	private SecretKey getSigninKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
