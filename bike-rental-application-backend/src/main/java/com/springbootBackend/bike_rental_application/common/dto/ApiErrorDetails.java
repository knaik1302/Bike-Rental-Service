package com.springbootBackend.bike_rental_application.common.dto;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorDetails {
	private String errorCode;
	private String message;
	private HttpStatus status;
	private Map<String, String> meta; //Optional field
}
