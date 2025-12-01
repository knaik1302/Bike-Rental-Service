package com.springbootBackend.bike_rental_application.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String Token;
}
