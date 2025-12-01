package com.springbootBackend.bike_rental_application.admin.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springbootBackend.bike_rental_application.admin.dto.AdminBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.ApiBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.BikeRequest;
import com.springbootBackend.bike_rental_application.admin.dto.PublicBikeResponse;
import com.springbootBackend.bike_rental_application.admin.mapper.AdminBikeMapper;
import com.springbootBackend.bike_rental_application.admin.mapper.PublicBikeMapper;
import com.springbootBackend.bike_rental_application.admin.service.BikeService;
import com.springbootBackend.bike_rental_application.common.utils.BusinessResult;
import com.springbootBackend.bike_rental_application.domain.entity.Bike;
import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;
import com.springbootBackend.bike_rental_application.domain.enums.BikeStatus;
import com.springbootBackend.bike_rental_application.domain.enums.ErrorCode;
import com.springbootBackend.bike_rental_application.domain.repository.AdminBikeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService{
	private final AdminBikeRepository bikeRepository;

	@Override
	public BusinessResult<ApiBikeResponse<PublicBikeResponse>> listAllBikes(BikeCategory category) {
		List<Bike> bikes;
		if(category != null) {
			bikes = bikeRepository.findByCategory(category);
		}
		bikes = bikeRepository.findAll();
		if(bikes.isEmpty()) {
			return BusinessResult.success(PublicBikeMapper.toApiResponse(List.of()), "No Bikes found");
		}
		List<PublicBikeResponse> responseList = bikes.stream().map(PublicBikeMapper::toResponse).toList();
		return BusinessResult.success(
				PublicBikeMapper.toApiResponse(responseList),
				"Data fetched Successfully..!!");
	}

	@Override
	public BusinessResult<PublicBikeResponse> getBikeById(Long id) {
		//Note Optional<Bike> will never be null
		return bikeRepository.findById(id)
	            .map(bike -> BusinessResult.success(
	                    PublicBikeMapper.toResponse(bike),
	                    "Data fetched successfully"))
	            .orElseGet(() -> BusinessResult.failure(
	                    ErrorCode.BIKE_NOT_FOUND,
	                    "Bike not found: " + id));
	}
	
	@Override
	public BusinessResult<AdminBikeResponse> addBike(BikeRequest bikeRequest) {
		Bike bikeEntity = AdminBikeMapper.toEntity(bikeRequest);
		Bike bike = bikeRepository.save(bikeEntity); //will never return null

		return BusinessResult.success(AdminBikeMapper.toResponse(bike),"Bike saved successfully");
	}

	@Override
	@Transactional
	public BusinessResult<AdminBikeResponse> updateBike(Long id, BikeRequest bikeRequest) {
		Optional<Bike> OptBike = bikeRepository.findById(id);
		if(OptBike.isEmpty()) {
			return BusinessResult.failure(ErrorCode.BIKE_NOT_FOUND, "Bike not found: " + id);
		}
		
		Bike bike = OptBike.get();
		if(bikeRequest.getName()!=null && !bikeRequest.getName().isBlank()) {
			bike.setName(bikeRequest.getName());
		}
		bike.setCategory(bikeRequest.getCategory());
		bike.setHourlyRate(bikeRequest.getHourlyRate());
		bike.setStatus(bikeRequest.getStatus());
		if(bikeRequest.getDescription()!=null && !bikeRequest.getDescription().isBlank()) {
			bike.setDescription(bikeRequest.getDescription());
		}
		
		Bike updated = bikeRepository.save(bike);
		return BusinessResult.success(AdminBikeMapper.toResponse(updated),"Bike updated successfully");
	}

	@Override
	public BusinessResult<AdminBikeResponse> deleteBike(Long id) {
		//bikeRepository.existsById(id) and bikeRepository.deleteById(id);
		//don't use above both Reason : 2db(exist and delete) query
		//Instead
		//bikeRepository.findById(id) and bikeRepository.delete(bike.get())
		//Reason : 1db read + 1 delete
		
		Optional<Bike> OptBike = bikeRepository.findById(id);
		if(OptBike.isEmpty()) {
			return BusinessResult.failure(ErrorCode.BIKE_NOT_FOUND, "Bike not found: " + id);
		}
		Bike bike = OptBike.get();
		bikeRepository.delete(bike);
		return BusinessResult.success(AdminBikeMapper.toResponse(bike),"Bike deleted successfully");
	}

	@Override
	public BusinessResult<AdminBikeResponse> updateBikeStatus(Long id, String status) {
		BikeStatus newStatus;
		try {
			newStatus = BikeStatus.valueOf(status.trim().toUpperCase());
		}
		catch(IllegalArgumentException ex) {
			return BusinessResult.failure(
	                ErrorCode.INVALID_STATUS,
	                "Invalid status value: " + status
	        );
		}
		Optional<Bike> OptBike = bikeRepository.findById(id);
		if(OptBike.isEmpty()) {
			return BusinessResult.failure(ErrorCode.BIKE_NOT_FOUND, "Bike not found: " + id);
		}
		Bike bike = OptBike.get();
		bike.setStatus(newStatus);
		
		Bike updated = bikeRepository.save(bike);
		return BusinessResult.success(AdminBikeMapper.toResponse(updated),"Bike status updated successfully");
	}

}
