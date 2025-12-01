package com.springbootBackend.bike_rental_application.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootBackend.bike_rental_application.auth.Mapper.AuthMapper;
import com.springbootBackend.bike_rental_application.auth.dto.LoginRequest;
import com.springbootBackend.bike_rental_application.auth.dto.LoginResponse;
import com.springbootBackend.bike_rental_application.auth.dto.RegisterRequest;
import com.springbootBackend.bike_rental_application.auth.dto.RegisterResponse;
import com.springbootBackend.bike_rental_application.auth.jwt.JwtTokenProvider;
import com.springbootBackend.bike_rental_application.common.utils.BusinessResult;
import com.springbootBackend.bike_rental_application.domain.entity.User;
import com.springbootBackend.bike_rental_application.domain.enums.ErrorCode;
import com.springbootBackend.bike_rental_application.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	
	//Register User
	public BusinessResult<RegisterResponse> registerUser(RegisterRequest req) {
		if(userRepository.existsByEmail(req.getEmail())) {
    		return BusinessResult.failure(ErrorCode.USER_EXIST_CONFLICT, "User Email already Exists");
    	}
    	User user = AuthMapper.toEntity(req);
    	user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
    	RegisterResponse response = AuthMapper.toResponse(userRepository.save(user)); //Register new User
		return BusinessResult.success(response, "User Created Successfully");
	}
	
	//Login
	public BusinessResult<LoginResponse> login(LoginRequest req) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
			User user = userRepository.findByEmail(req.getEmail()).orElseThrow();
			String token = tokenProvider.CreateToken(String.valueOf(user.getId()), user.getRole().name());
			return BusinessResult.success(AuthMapper.toResponse(token), user.getName() + " is logged in successfully..!!");			
		}
		catch (AuthenticationException e) {
			return BusinessResult.failure(ErrorCode.UNAUTHORIZED, "Invalid Credentials");		
		}
	}
	
}
