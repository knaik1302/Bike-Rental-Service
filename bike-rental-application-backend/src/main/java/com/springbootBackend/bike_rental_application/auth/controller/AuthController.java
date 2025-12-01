package com.springbootBackend.bike_rental_application.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootBackend.bike_rental_application.auth.dto.LoginRequest;
import com.springbootBackend.bike_rental_application.auth.dto.RegisterRequest;
import com.springbootBackend.bike_rental_application.auth.dto.RegisterResponse;
import com.springbootBackend.bike_rental_application.auth.service.UserService;
import com.springbootBackend.bike_rental_application.common.utils.ApiResponseFactory;
import com.springbootBackend.bike_rental_application.common.utils.BusinessResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
    	BusinessResult<RegisterResponse> result = userService.registerUser(req);
    	return ApiResponseFactory.fromBusinessResult(result);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
    	return ApiResponseFactory.fromBusinessResult(userService.login(req));
    }
}
