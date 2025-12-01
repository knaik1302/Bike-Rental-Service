package com.springbootBackend.bike_rental_application.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingRequest {
	private Long bikeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
