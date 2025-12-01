package com.springbootBackend.bike_rental_application.admin.service;

import com.springbootBackend.bike_rental_application.admin.dto.AdminBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.ApiBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.BikeRequest;
import com.springbootBackend.bike_rental_application.admin.dto.PublicBikeResponse;
import com.springbootBackend.bike_rental_application.common.utils.BusinessResult;
import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;

public interface BikeService {
	
	BusinessResult<ApiBikeResponse<PublicBikeResponse>> listAllBikes(BikeCategory category);
	
	BusinessResult<PublicBikeResponse> getBikeById(Long id);

	BusinessResult<AdminBikeResponse> addBike(BikeRequest bikeRequest); 
	
	BusinessResult<AdminBikeResponse> updateBike(Long id, BikeRequest bikeRequest);
	
	BusinessResult<AdminBikeResponse> deleteBike(Long id);
	
	BusinessResult<AdminBikeResponse> updateBikeStatus(Long id, String status);
}
