package com.springbootBackend.bike_rental_application.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springbootBackend.bike_rental_application.domain.entity.Booking;
import com.springbootBackend.bike_rental_application.domain.enums.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	@Query("""
			SELECT b FROM Booking b
			WHERE b.bike.id = :bikeId
			AND b.bookingStatus IN :blockingStatuses
			AND NOT (b.endTime <= :startTime OR b.startTime >= :endTime)
		""")
	List<Booking> findOverlappingBooking(@Param("bikeId") Long bikeId,
			@Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime,
			@Param("blockingStatuses") List<BookingStatus> blockingStatuses);
}
