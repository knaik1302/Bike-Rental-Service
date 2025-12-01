package com.springbootBackend.bike_rental_application.admin.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springbootBackend.bike_rental_application.admin.dto.ApiBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.PublicBikeResponse;
import com.springbootBackend.bike_rental_application.domain.entity.Bike;
import com.springbootBackend.bike_rental_application.domain.enums.BikeStatus;

@Component
public class PublicBikeMapper {
	
	public static PublicBikeResponse toResponse(Bike bike) {
		return PublicBikeResponse.builder()
				.id(bike.getId())
				.name(bike.getName())
				.category(bike.getCategory())
				.hourlyRate(bike.getHourlyRate())
				.status(BikeStatus.AVAILABLE==bike.getStatus())
				.description(bike.getDescription())
				.build();
	}
	
	public static <T> ApiBikeResponse<T> toApiResponse(List<T> response){
		return ApiBikeResponse.<T>builder()
				.items(response)
				.totalCount(response.size())
				.build();
	}
}
