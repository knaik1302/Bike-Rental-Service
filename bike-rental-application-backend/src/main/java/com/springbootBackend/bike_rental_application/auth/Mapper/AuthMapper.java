package com.springbootBackend.bike_rental_application.auth.Mapper;

import org.springframework.stereotype.Component;

import com.springbootBackend.bike_rental_application.auth.dto.LoginResponse;
import com.springbootBackend.bike_rental_application.auth.dto.RegisterRequest;
import com.springbootBackend.bike_rental_application.auth.dto.RegisterResponse;
import com.springbootBackend.bike_rental_application.domain.entity.User;
import com.springbootBackend.bike_rental_application.domain.enums.Role;

@Component
public class AuthMapper {
	
	public static User toEntity(RegisterRequest req) {
		return User.builder()
    			.name(req.getName())
    			.email(req.getEmail())
    			.role(Role.CUSTOMER)
    			.phone(req.getPhone())
    			.build();
	}
	
	public static RegisterResponse toResponse(User user) {
		return RegisterResponse.builder()
				.name(user.getName())
				.email(user.getEmail())
				.build();
	}
	
	public static LoginResponse toResponse(String token) {
		return LoginResponse.builder()
				.Token(token)
				.build();
	}
}
