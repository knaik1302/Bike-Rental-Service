package com.springbootBackend.bike_rental_application.domain.entity;

import java.math.BigDecimal;

import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;
import com.springbootBackend.bike_rental_application.domain.enums.BikeStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="bikes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //to simplify the creation of complex objects
public class Bike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

    @Enumerated(EnumType.STRING)
    private BikeCategory category;
	
	private BigDecimal hourlyRate;
	
	@Enumerated(EnumType.STRING)
    private BikeStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;
}
