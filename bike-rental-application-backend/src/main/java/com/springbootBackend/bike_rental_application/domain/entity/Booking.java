package com.springbootBackend.bike_rental_application.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.springbootBackend.bike_rental_application.domain.enums.BookingStatus;
import com.springbootBackend.bike_rental_application.domain.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //to simplify the creation of complex objects
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id")
    private Bike bike;
	
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal totalAmount;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
