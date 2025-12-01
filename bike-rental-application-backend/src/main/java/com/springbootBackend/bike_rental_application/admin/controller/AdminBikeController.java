package com.springbootBackend.bike_rental_application.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootBackend.bike_rental_application.admin.dto.AdminBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.ApiBikeResponse;
import com.springbootBackend.bike_rental_application.admin.dto.BikeRequest;
import com.springbootBackend.bike_rental_application.admin.dto.PublicBikeResponse;
import com.springbootBackend.bike_rental_application.admin.service.BikeService;
import com.springbootBackend.bike_rental_application.common.utils.ApiResponseFactory;
import com.springbootBackend.bike_rental_application.common.utils.BusinessResult;
import com.springbootBackend.bike_rental_application.domain.entity.Bike;
import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/bikes")
@RequiredArgsConstructor
public class AdminBikeController {
	private final BikeService bikeService;
	
	@GetMapping
	public ResponseEntity<?> listAllBikes(@RequestParam(value = "category", required = false) BikeCategory category) {
		BusinessResult<ApiBikeResponse<PublicBikeResponse>> result = bikeService.listAllBikes(category);
		return ApiResponseFactory.fromBusinessResult(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBikeById(@PathVariable Long id) {
		BusinessResult<PublicBikeResponse> result = bikeService.getBikeById(id);
		return ApiResponseFactory.fromBusinessResult(result);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addBike(@RequestBody BikeRequest bikeRequest) {
		BusinessResult<AdminBikeResponse> result = bikeService.addBike(bikeRequest);
		return ApiResponseFactory.fromBusinessResult(result);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateBike(@PathVariable Long id, @RequestBody BikeRequest bikeRequest) {
		BusinessResult<AdminBikeResponse> result = bikeService.updateBike(id, bikeRequest);
		return ApiResponseFactory.fromBusinessResult(result);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteBike(@PathVariable Long id) {
		BusinessResult<AdminBikeResponse> result = bikeService.deleteBike(id);
		return ApiResponseFactory.fromBusinessResult(result);
	}
	
	@PatchMapping("/{id}/status")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateBikeStatus(@PathVariable Long id, @RequestParam String status) {
		BusinessResult<AdminBikeResponse> result = bikeService.updateBikeStatus(id, status);
		return ApiResponseFactory.fromBusinessResult(result);
	}
}
