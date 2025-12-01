package com.springbootBackend.bike_rental_application.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;
import com.springbootBackend.bike_rental_application.domain.enums.BookingStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
	//Booking details
	private Long uuid;
	private BigDecimal totalAmount;
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus bookingStatus;
	
	//User details
	private String userName;
	
	//Bike details
	private String bikeName;
	private BikeCategory bikeCategory;
}
