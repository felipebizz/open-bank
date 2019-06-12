package com.openbank.handler;

import com.openbank.error.ErrorDetails;
import com.openbank.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not Found!")
                .details(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}