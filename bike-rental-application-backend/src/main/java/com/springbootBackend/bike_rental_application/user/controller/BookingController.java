package com.springbootBackend.bike_rental_application.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootBackend.bike_rental_application.user.dto.BookingRequest;
import com.springbootBackend.bike_rental_application.user.dto.BookingResponse;
import com.springbootBackend.bike_rental_application.user.service.BookingService;
import com.springbootBackend.bike_rental_application.common.utils.ApiResponseFactory;
import com.springbootBackend.bike_rental_application.common.utils.BusinessResult;
import com.springbootBackend.bike_rental_application.domain.entity.Booking;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
	private final BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BookingRequest req, Authentication auth){
		Long userId = Long.parseLong((String)auth.getPrincipal());
		BusinessResult<BookingResponse> result = bookingService.createBooking(userId, req.getBikeId(), req.getStartTime(), req.getEndTime());
		return ApiResponseFactory.fromBusinessResult(result);
	}
}
