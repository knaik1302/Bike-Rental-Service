package com.springbootBackend.bike_rental_application.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
	@Value("${app.cors.allowed-origins}")
	private String allowedOrigins;
	
	@Value("${app.cors.allowed-methods}")
	private String allowedMethods;
	
	@Value("${app.cors.allowed-headers}")
	private String allowedHeaders;
	
	@Value("${app.cors.exposed-headers}")
	private String exposedHeaders;
	
	@Value("${app.cors.max-age}")
	private Long maxAge;
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		
		List<String> origins = Arrays.stream(allowedOrigins.split(","))
				.map(String::trim)
				.toList();
		
		List<String> methods = Arrays.stream(allowedMethods.split(","))
				.map(String::trim)
				.toList();
		
		List<String> headers = Arrays.stream(allowedHeaders.split(","))
				.map(String::trim)
				.toList();
		
		List<String> expHeaders = Arrays.stream(exposedHeaders.split(","))
				.map(String::trim)
				.toList();
		
		config.setAllowedOrigins(origins);
		config.setAllowedMethods(methods);
		config.setAllowedHeaders(headers);
		config.setExposedHeaders(expHeaders);
		config.setAllowCredentials(true);
		config.setMaxAge(maxAge);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
}
