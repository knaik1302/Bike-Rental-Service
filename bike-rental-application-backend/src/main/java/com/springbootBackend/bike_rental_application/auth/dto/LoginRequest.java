package com.springbootBackend.bike_rental_application.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String email;
    private String password;
}
