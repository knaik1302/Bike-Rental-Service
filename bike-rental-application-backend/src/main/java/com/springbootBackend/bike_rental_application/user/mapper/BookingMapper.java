package com.springbootBackend.bike_rental_application.user.mapper;

import com.springbootBackend.bike_rental_application.domain.entity.Booking;
import com.springbootBackend.bike_rental_application.user.dto.BookingRequest;
import com.springbootBackend.bike_rental_application.user.dto.BookingResponse;

public class BookingMapper {
	public static Booking toEntity(BookingRequest req) {
		return Booking.builder()
				.build();
	}
	
	public static BookingResponse toResponse(Booking booking) {
		return BookingResponse.builder()
				.uuid(booking.getId())
				.totalAmount(booking.getTotalAmount())
				.startTime(booking.getStartTime())
				.endTime(booking.getEndTime())
				.bookingStatus(booking.getBookingStatus())
				.userName(booking.getUser().getName())
				.bikeName(booking.getBike().getName())
				.bikeCategory(booking.getBike().getCategory())
				.build();
	}
}
