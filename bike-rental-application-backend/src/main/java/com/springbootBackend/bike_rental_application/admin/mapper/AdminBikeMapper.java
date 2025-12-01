package com.springbootBackend.bike_rental_application.admin.mapper;

import org.springframework.stereotype.Component;

import com.springbootBackend.bike_rental_application.admin.dto.AdminBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.BikeRequest;
import com.springbootBackend.bike_rental_application.domain.entity.Bike;

@Component
public class AdminBikeMapper {
	
	public static Bike toEntity(BikeRequest bikeRequest) {
		return Bike.builder()
				.name(bikeRequest.getName())
				.category(bikeRequest.getCategory())
				.hourlyRate(bikeRequest.getHourlyRate())
				.status(bikeRequest.getStatus())
				.description(bikeRequest.getDescription())
				.build();
	}
	
	public static AdminBikeResponse toResponse(Bike bike) {
		return AdminBikeResponse.builder()
				.id(bike.getId())
				.name(bike.getName())
				.category(bike.getCategory())
				.hourlyRate(bike.getHourlyRate())
				.status(bike.getStatus())
				.description(bike.getDescription())
				.build();
	}
}
