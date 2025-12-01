package com.springbootBackend.bike_rental_application.domain.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	// We can get Code 400 from HttpStatus.BAD_REQUEST.value()
	
	//ConstructorName(CustomizedErroCode, httpStatusCode, HttpStatus)
	VALIDATION_ERROR("ERR_VALIDATION", 400, HttpStatus.BAD_REQUEST),
    BAD_REQUEST("ERR_BAD_REQUEST", 400, HttpStatus.BAD_REQUEST),
    INVALID_STATUS("ERR_INVALID_STATUS", 400, HttpStatus.BAD_REQUEST),
    INVALID_TRIP_DURATION("ERR_INVALID_TRIP_DURATION", 400, HttpStatus.BAD_REQUEST),
    
    UNAUTHORIZED("ERR_UNAUTHORIZED", 401, HttpStatus.UNAUTHORIZED),
    FORBIDDEN("ERR_FORBIDDEN", 403, HttpStatus.FORBIDDEN),
    
    NOT_FOUND("ERR_NOT_FOUND", 404, HttpStatus.NOT_FOUND),
    BIKE_NOT_FOUND("ERR_BIKE_NOT_FOUND", 404, HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("ERR_USER_NOT_FOUND", 404, HttpStatus.NOT_FOUND),
    
    CONFLICT("ERR_CONFLICT", 409, HttpStatus.CONFLICT),
    USER_EXIST_CONFLICT("ERR_USER_EXIST_CONFLICT", 409, HttpStatus.CONFLICT),
    BIKE_BOOKING_CONFLICT("ERR_BIKE_BOOKING_CONFLICT", 409, HttpStatus.CONFLICT),
    
    BUSINESS_ERROR("ERR_BUSINESS", 422, HttpStatus.UNPROCESSABLE_CONTENT),
    INTERNAL_ERROR("ERR_INTERNAL", 500, HttpStatus.INTERNAL_SERVER_ERROR);
	
	private final String errorCode;
	private final int httpStatusCode;
	private final HttpStatus httpStatus;
	
	ErrorCode(String code, int httpStatusCode, HttpStatus httpStatus){
		this.errorCode = code;
		this.httpStatusCode = httpStatusCode;
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
