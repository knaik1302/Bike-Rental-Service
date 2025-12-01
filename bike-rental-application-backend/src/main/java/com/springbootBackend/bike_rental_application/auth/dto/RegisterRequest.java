package com.springbootBackend.bike_rental_application.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
	private String name;
    private String email;
    private String password;
    private String phone;
}
