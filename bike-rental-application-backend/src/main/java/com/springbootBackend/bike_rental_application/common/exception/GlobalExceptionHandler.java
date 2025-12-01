package com.springbootBackend.bike_rental_application.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.springbootBackend.bike_rental_application.common.dto.ApiEnvelope;
import com.springbootBackend.bike_rental_application.common.utils.ApiResponseFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// ----------------------------------------
    // 1. Handle validation @Valid errors
    // ----------------------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        return ApiResponseFactory.error(
                "VALIDATION_FAILED",
                "Validation error occurred",
                HttpStatus.BAD_REQUEST,
                fieldErrors
        );
    }

    // ----------------------------------------
    // 🔰 2. Handle validation errors (no request body)
    // ----------------------------------------
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleBindErrors(BindException ex) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        return ApiResponseFactory.error(
                "VALIDATION_FAILED",
                "Invalid input provided",
                HttpStatus.BAD_REQUEST,
                fieldErrors
        );
    }

    // ----------------------------------------
    // 🔰 3. Handle malformed JSON
    // ----------------------------------------
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleMalformedJson(HttpMessageNotReadableException ex) {

        return ApiResponseFactory.error(
                "INVALID_JSON",
                "Malformed JSON in request body",
                HttpStatus.BAD_REQUEST
        );
    }

    // ----------------------------------------
    // 🔰 4. JSON Mapping errors
    // ----------------------------------------
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleJsonMapping(JsonMappingException ex) {

        return ApiResponseFactory.error(
                "JSON_MAPPING_ERROR",
                ex.getOriginalMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // ----------------------------------------
    // 🔰 5. Database errors
    // ----------------------------------------
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleDatabaseError(DataAccessException ex) {

        return ApiResponseFactory.error(
                "DATABASE_ERROR",
                "A database error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // ----------------------------------------
    // 🔰 6. IllegalArgumentException: bad input
    // ----------------------------------------
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleIllegalArgument(IllegalArgumentException ex) {

        return ApiResponseFactory.error(
                "BAD_REQUEST",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // ----------------------------------------
    // 🔰 7. IllegalStateException: logic violation
    // ----------------------------------------
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleIllegalState(IllegalStateException ex) {

        return ApiResponseFactory.error(
                "ILLEGAL_STATE",
                ex.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    // ----------------------------------------
    // 🔰 8. Access Denied Exception
    // ----------------------------------------
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiEnvelope<Object>> handleAccessDenied(Exception ex) {

        ex.printStackTrace();  // log to console, or use Logger

        return ApiResponseFactory.error(
                "ACCESS_DENIED_ERROR",
                "You do not have permission to perform this action",
                HttpStatus.FORBIDDEN
        );
    }
    
    // ----------------------------------------
    // 🔰 9. Fallback for ALL uncaught exceptions
    // ----------------------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiEnvelope<Object>> handleUnexpectedError(Exception ex) {

        ex.printStackTrace();  // log to console, or use Logger

        return ApiResponseFactory.error(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    
}


