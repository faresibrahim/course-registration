package com.example.backend.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice 
public class GlobalExceptionHandler {

 
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleNotFound(NotFoundException ex) {
    ErrorResponseDto body = new ErrorResponseDto(404, ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorResponseDto> handleConflict(ConflictException ex) {
    ErrorResponseDto body = new ErrorResponseDto(409, ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }

  @ExceptionHandler(Exception.class)   // safety net
  public ResponseEntity<ErrorResponseDto> handleUnexpected(Exception ex) {
    ErrorResponseDto body = new ErrorResponseDto(500, "Something went wrong");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
