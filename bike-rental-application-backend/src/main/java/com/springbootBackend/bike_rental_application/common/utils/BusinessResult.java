package com.springbootBackend.bike_rental_application.common.utils;

import org.springframework.http.HttpStatus;

import com.springbootBackend.bike_rental_application.domain.enums.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessResult<T> {
	private final boolean success;
	private final T data;
	private final String errorCode;
	private final String message;
	private final HttpStatus status;
	
	private BusinessResult(boolean success, T data, String errorCode, String message, HttpStatus status){
		this.success = success;
		this.data = data;
		this.errorCode = errorCode;
		this.message = message;
		this.status = status.BAD_REQUEST;
	}
	
	public static <T> BusinessResult<T> success(T data, String message){
		return new BusinessResult<T>(true, data, null, message, HttpStatus.OK);
	}
	
	public static <T> BusinessResult<T> failure(ErrorCode errorCode, String message){
		return new BusinessResult<T>(false, null, errorCode.getErrorCode(), message, errorCode.getHttpStatus());
	}
}
