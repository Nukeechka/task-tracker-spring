package com.craftzdev.tasktracker.util;

import com.craftzdev.tasktracker.util.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e, WebRequest request) {
        log.warn("NotFoundException occurred: {} - Path: {}", e.getMessage(), request.getDescription(false));
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e, WebRequest request) {
        log.warn("Validation failed: {}", e.getMessage());
        StringBuilder errors = new StringBuilder("Validation errors: ");
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; "));

        return buildErrorResponse(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e, WebRequest request) {
        log.error("An unexpected error occurred: {} - Path: {}", e.getMessage(), request.getDescription(false), e);
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred", System.currentTimeMillis());
        return buildErrorResponse(errorResponse.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message, System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, status);
    }
}
