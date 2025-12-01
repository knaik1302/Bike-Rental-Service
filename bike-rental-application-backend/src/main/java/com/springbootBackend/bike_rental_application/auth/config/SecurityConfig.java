package com.springbootBackend.bike_rental_application.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springbootBackend.bike_rental_application.auth.jwt.JwtAuthenticationFilter;
import com.springbootBackend.bike_rental_application.auth.jwt.JwtTokenProvider;
import com.springbootBackend.bike_rental_application.auth.service.CustomUserDetailsService;
import com.springbootBackend.bike_rental_application.common.exception.CustomAccessDeniedHandler;
import com.springbootBackend.bike_rental_application.common.exception.CustomAuthEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	private final JwtTokenProvider jwtTokenProvider;
	private final CustomUserDetailsService customUserDetailsService;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final CustomAuthEntryPoint customAuthEntryPoint;
	
	public SecurityConfig(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService, CustomAccessDeniedHandler customAccessDeniedHandler, CustomAuthEntryPoint customAuthEntryPoint) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.customUserDetailsService = customUserDetailsService;
		this.customAccessDeniedHandler = customAccessDeniedHandler;
		this.customAuthEntryPoint = customAuthEntryPoint;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtTokenProvider);
		
		http
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**", "/api/v1/bikes/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
		.exceptionHandling(ex -> 
			ex.accessDeniedHandler(customAccessDeniedHandler)
				.authenticationEntryPoint(customAuthEntryPoint))
		.userDetailsService(customUserDetailsService)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
}
