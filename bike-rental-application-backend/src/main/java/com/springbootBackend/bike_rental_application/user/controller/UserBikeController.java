package com.springbootBackend.bike_rental_application.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootBackend.bike_rental_application.domain.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import com.springbootBackend.bike_rental_application.domain.entity.Bike;
import com.springbootBackend.bike_rental_application.domain.enums.BikeCategory;

@RestController
//@RequestMapping("/api/bike/userbike")
@RequiredArgsConstructor
public class UserBikeController {
//	private final BikeRepository bikeRepository;
	
//	@GetMapping
//	public ResponseEntity<List<Bike>> listBike(@RequestParam(value = "category", required = false) BikeCategory category){
//		if(category !=null ) return ResponseEntity.ok(bikeRepository.findByCategory(category));
//		return ResponseEntity.ok(bikeRepository.findAll());
//	}
	
}
