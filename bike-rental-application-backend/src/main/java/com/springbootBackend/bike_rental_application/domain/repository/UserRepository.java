package com.springbootBackend.bike_rental_application.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootBackend.bike_rental_application.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	boolean  existsByEmail(String email);
}
