package com.springbootBackend.bike_rental_application.common.exception;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.springbootBackend.bike_rental_application.common.utils.ApiResponseFactory;
import com.springbootBackend.bike_rental_application.domain.enums.ErrorCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ApiResponseFactory.write(ErrorCode.UNAUTHORIZED, response, "Authentication required");
		
	}
}