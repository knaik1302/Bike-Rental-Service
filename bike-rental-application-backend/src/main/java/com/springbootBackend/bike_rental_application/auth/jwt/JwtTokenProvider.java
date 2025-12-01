package com.springbootBackend.bike_rental_application.auth.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springbootBackend.bike_rental_application.domain.enums.JwtStatus;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private final Key key;
	private final long validityInMs;
	
	public JwtTokenProvider(@Value("${jwt.secret}") String secret,
			@Value("${jwt.expirationMs}") long validityInMs) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.validityInMs = validityInMs;
	}
	
	public String CreateToken(String subject, String role) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + validityInMs);
		
		return Jwts.builder()
				.setSubject(subject)
				.claim("role", role)
				.setIssuedAt(now)
				.setExpiration(expiry)
				.signWith(key)
				.compact();
	}
	
	public JwtStatus validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return JwtStatus.VALID;
		}
		catch(ExpiredJwtException e) {
			return JwtStatus.EXPIRED;
		}
		catch(JwtException | IllegalArgumentException e) {
			return JwtStatus.INVALID;
		}
	}
	
	public String getSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public String getRole(String token) {
		return (String)Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role");
	}
}
