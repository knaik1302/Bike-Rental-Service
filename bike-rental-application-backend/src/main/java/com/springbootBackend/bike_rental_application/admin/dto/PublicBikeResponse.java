package com.springbootBackend.bike_rental_application.admin.dto;

import java.math.BigDecimal;

import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;
import com.springbootBackend.bike_rental_application.domain.enums.BikeStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublicBikeResponse {
	private Long id;
	private String name;
	private BikeCategory category;
	private BigDecimal hourlyRate;
	private boolean status;
	private String description;
}
