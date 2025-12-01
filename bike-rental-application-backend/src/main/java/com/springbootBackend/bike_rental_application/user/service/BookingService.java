package com.springbootBackend.bike_rental_application.user.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springbootBackend.bike_rental_application.domain.repository.BikeRepository;
import com.springbootBackend.bike_rental_application.domain.repository.BookingRepository;
import com.springbootBackend.bike_rental_application.domain.repository.UserRepository;
import com.springbootBackend.bike_rental_application.user.dto.BookingResponse;
import com.springbootBackend.bike_rental_application.user.mapper.BookingMapper;
import com.springbootBackend.bike_rental_application.admin.mapper.AdminBikeMapper;
import com.springbootBackend.bike_rental_application.common.utils.BusinessResult;
import com.springbootBackend.bike_rental_application.domain.entity.Bike;
import com.springbootBackend.bike_rental_application.domain.entity.Booking;
import com.springbootBackend.bike_rental_application.domain.entity.User;
import com.springbootBackend.bike_rental_application.domain.enums.BikeStatus;
import com.springbootBackend.bike_rental_application.domain.enums.BookingStatus;
import com.springbootBackend.bike_rental_application.domain.enums.ErrorCode;
import com.springbootBackend.bike_rental_application.domain.enums.PaymentStatus;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	private final UserRepository userRepository;
	private final BikeRepository bikeRepository;
	private final BookingRepository bookingRepository;
	
	private static final List<BookingStatus> BLOCKING_STATUSES = 
			List.of(BookingStatus.CONFIRMED, BookingStatus.ON_RENT, BookingStatus.REQUESTED);
	
	@Transactional
	public BusinessResult<BookingResponse> createBooking(Long userId, Long bikeId, LocalDateTime start, LocalDateTime end) {
		//--------------------------------------
		// Validation 1 : Trip time validation
		//--------------------------------------
		if(!start.isBefore(end)) {
			return BusinessResult.failure(ErrorCode.INVALID_TRIP_DURATION, "Trip start time should be before trip end time");
		}
		if(start.isBefore(LocalDateTime.now())) {
			return BusinessResult.failure(ErrorCode.INVALID_TRIP_DURATION, "Trip start time should be future time");
		}
		
		//--------------------------------
		// Validation 2 : User validation
		//----------------------------------
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			return BusinessResult.failure(ErrorCode.USER_NOT_FOUND, "User not found: " + userId);
		}
		
		//--------------------------------------------------------------------------
		// Validation 3 : Bike availability validation
		// Locking at entity-level: fetch bike and rely on DB transaction isolation
		//--------------------------------------------------------------------------
		Bike bike = bikeRepository.findById(bikeId).orElse(null);
		// Bike Validation (i)
		if(bike == null) {
			return BusinessResult.failure(ErrorCode.BIKE_NOT_FOUND, "Bike not found : " + bikeId);
		}
		// Bike Validation (ii)
		if (bike.getStatus() != BikeStatus.AVAILABLE) {
			return BusinessResult.failure(ErrorCode.BIKE_BOOKING_CONFLICT, "Bike is not available to book");
		}
		// Bike Validation (iii)
		List<Booking> overlaps = bookingRepository.findOverlappingBooking(bikeId, start, end, BLOCKING_STATUSES);
		if(!overlaps.isEmpty()) {
			return BusinessResult.failure(ErrorCode.BIKE_BOOKING_CONFLICT, "This bike is already reserved for the selected period");
		}
		
		BigDecimal amount = calculatePrice(bike, start, end);
		
		Booking booking = Booking.builder()
				.user(user)
				.bike(bike)
				.startTime(start)
				.endTime(end)
				.totalAmount(amount)
				.bookingStatus(BookingStatus.REQUESTED)
				.paymentStatus(PaymentStatus.PENDING)
				.build();
		Booking booked = bookingRepository.save(booking);
		
		return BusinessResult.success(BookingMapper.toResponse(booked), "Bike booked successfully");
	}
	
	private BigDecimal calculatePrice(Bike bike, LocalDateTime start, LocalDateTime end) {
		long minutes = Duration.between(start, end).toMinutes();
		BigDecimal hours = BigDecimal.valueOf(Math.ceil(minutes / 60.0));
		return bike.getHourlyRate().multiply(hours);
	}
}
