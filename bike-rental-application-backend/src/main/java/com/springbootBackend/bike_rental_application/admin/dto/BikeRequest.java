package com.springbootBackend.bike_rental_application.admin.dto;

import java.math.BigDecimal;

import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;
import com.springbootBackend.bike_rental_application.domain.enums.BikeStatus;

import lombok.Data;

@Data
public class BikeRequest {
	private String name;
	private BikeCategory category;
	private BigDecimal hourlyRate;
	private BikeStatus status;
	private String description;
}
