package com.springbootBackend.bike_rental_application.common.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiEnvelope<T> {
	private boolean success;		//true --> Success && false --> Failure
	private T data;					//success case if you want to send data to front end
	private String message;			//success case if you want to display any message
	private ApiErrorDetails error;	//failure case error details will be visible
	private Instant timeStamp;
}
