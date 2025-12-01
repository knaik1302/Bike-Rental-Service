package com.springbootBackend.bike_rental_application.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootBackend.bike_rental_application.domain.entity.Bike;
import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;

public interface BikeRepository extends JpaRepository<Bike, Long>{
	List<Bike> findByCategory(BikeCategory category);
}
