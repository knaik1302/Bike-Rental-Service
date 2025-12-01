package com.springbootBackend.bike_rental_application.admin.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiBikeResponse<T> {
	private List<T> items;
	private int totalCount;
}
