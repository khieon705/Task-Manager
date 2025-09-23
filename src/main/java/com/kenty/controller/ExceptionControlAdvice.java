package com.kenty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kenty.exception.NoTaskFoundException;
import com.kenty.model.ErrorMessage;

@RestControllerAdvice
public class ExceptionControlAdvice {
    @ExceptionHandler(NoTaskFoundException.class)
    public ResponseEntity<ErrorMessage> exceptionNoTaskFoundHandler(NoTaskFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
    }
}
