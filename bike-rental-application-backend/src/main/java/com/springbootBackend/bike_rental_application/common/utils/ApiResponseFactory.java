package com.springbootBackend.bike_rental_application.common.utils;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springbootBackend.bike_rental_application.common.dto.ApiEnvelope;
import com.springbootBackend.bike_rental_application.common.dto.ApiErrorDetails;
import com.springbootBackend.bike_rental_application.domain.enums.ErrorCode;

import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class ApiResponseFactory {
	
	// -----------------
    // ⭐ Success
    // -----------------
	//Success method returns of type <T>
	public static <T> ResponseEntity<ApiEnvelope<T>> success(T data, String successMessage){	
		//ApiEnvelope.builder().build(); --> returns type of object but below code returns type <T>
		ApiEnvelope<T> envelope = ApiEnvelope.<T>builder()
				.success(true)
				.data(data)
				.message(successMessage)
				.timeStamp(Instant.now())
				.build();
		
		return ResponseEntity.ok(envelope);
	}
	
	// -----------------
    // ⭐ Failure
    // -----------------
	//failure method returns of type Object
	//Generic type must be parameterized
	public static ResponseEntity<ApiEnvelope<Object>> failure(String errorCode, String message, HttpStatus status){
		ApiErrorDetails errorDetails = ApiErrorDetails.builder()
				.errorCode(errorCode)
				.message(message)
				.status(status)
				.build();
		
		ApiEnvelope<Object> envelope = ApiEnvelope.builder()
				.error(errorDetails)
				.timeStamp(Instant.now())
				.build();
		
		return ResponseEntity.status(status).body(envelope);
	}
	
	// ---------------------------------------------------------------
    // ⭐ Helper Function 1 : Every Controller will call this method.
	// * Success and failure will be invoked internally
    // ---------------------------------------------------------------
	public static <T> ResponseEntity<ApiEnvelope<T>> fromBusinessResult(BusinessResult<T> result){
		if(result.isSuccess()) {
			return success(result.getData(), result.getMessage());
		}
		
		// (ResponseEntity<?>) casting is always safe..
		// During run time, only "ResponseEntity" exists.. (no generic type exists during run time)
		// Because JVM does not know the generic type — only the compiler checks them
		// ResponseEntity<ApiEnvelope<Object>> --> (ResponseEntity<?>) --> (ResponseEntity<ApiEnvelope<T>>)
		// (i) Cast to (ResponseEntity<?>)  ResponseEntity<?> means "a ResponseEntity of some unknown type”. So anything of type ResponseEntity<X> fits
		// (ii) Cast to (ResponseEntity<ApiEnvelope<T>>)  At runtime, both are just ResponseEntity
		// This type cast does NOT change the actual object. So the cast is permitted (with a warning)
		return (ResponseEntity<ApiEnvelope<T>>) (ResponseEntity<?>) failure(result.getErrorCode(), result.getMessage(), result.getStatus());
	}
	
	// --------------------------------------------------
    // ⭐ Helper Function 2 : For giving response without Meta data
    // --------------------------------------------------
    public static ResponseEntity<ApiEnvelope<Object>> error(String errorCode, String message, HttpStatus status) {
        return error(errorCode, message, status, null);
    }

    // ---------------------------------------------------------
    // ⭐ Helper Function 3 : For giving response with Meta data
    // ---------------------------------------------------------
    public static ResponseEntity<ApiEnvelope<Object>> error(String errorCode, String message, HttpStatus status, Map<String, String> meta) {
        ApiErrorDetails details = ApiErrorDetails.builder()
                .errorCode(errorCode)
                .message(message)
                .status(status)
                .meta(meta)
                .build();

        ApiEnvelope<Object> envelope = ApiEnvelope.builder()
                .success(false)
                .error(details)
                .timeStamp(Instant.now())
                .build();

        return ResponseEntity.status(status).body(envelope);
    }
    
    // ----------------------------------------------------------------------------------------------------
    // ⭐ Helper Function 4 : For giving negative case response of filters (accessDeniedHandler, JwtFilter)
    // ----------------------------------------------------------------------------------------------------
    public static void write(ErrorCode errorCode, HttpServletResponse response, String message) throws IOException{
        ApiErrorDetails details = ApiErrorDetails.builder()
                .errorCode(errorCode.getErrorCode())
                .message(message)
                .status(errorCode.getHttpStatus())
                .build();

        ApiEnvelope<Object> envelope = ApiEnvelope.builder()
                .success(false)
                .error(details)
                .timeStamp(Instant.now())
                .build();
        
        response.setStatus(errorCode.getHttpStatusCode());
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(envelope));
    }
}
