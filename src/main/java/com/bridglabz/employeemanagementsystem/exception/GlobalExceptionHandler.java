package com.bridglabz.employeemanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IdInvalidException.class)
    public ResponseEntity<ErrorResponse> handleIdNotFound(IdInvalidException exception){

        ErrorResponse error = new ErrorResponse(404, exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);

    }

}
