package com.springbootBackend.bike_rental_application.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
	private String name;
	private String email;
}
