package com.springbootBackend.bike_rental_application.auth.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springbootBackend.bike_rental_application.common.utils.ApiResponseFactory;
import com.springbootBackend.bike_rental_application.domain.enums.ErrorCode;
import com.springbootBackend.bike_rental_application.domain.enums.JwtStatus;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = null;
		String token = null;
		
		//Get the Header from Request Header
		header = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		//Let pass public end points
//		if(!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
//			filterChain.doFilter(request, response);
//			return;
//		}

		if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			token = header.substring(7);
			
			if(token==null || token.isEmpty()) {
				ApiResponseFactory.write(ErrorCode.UNAUTHORIZED, response, "Token should not be empty");
				return;
			}
			
			//Validate JWT Token and get the Status
			JwtStatus jwtStatus = jwtTokenProvider.validateToken(token);
			
			if(jwtStatus == JwtStatus.INVALID) {
				ApiResponseFactory.write(ErrorCode.UNAUTHORIZED, response, "Token is invalid");
				return;
			}
			
			if(jwtStatus == JwtStatus.EXPIRED) {
				ApiResponseFactory.write(ErrorCode.UNAUTHORIZED, response, "Token is Expired");
				return;
			}
			
			String subject = jwtTokenProvider.getSubject(token);
			String role = jwtTokenProvider.getRole(token);
			var auth = new UsernamePasswordAuthenticationToken(subject, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);	
	}
}
