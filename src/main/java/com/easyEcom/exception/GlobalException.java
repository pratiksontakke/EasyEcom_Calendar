package com.easyEcom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e, WebRequest req) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException nfe, WebRequest req) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MyErrorDetails> illegalArgumentException(IllegalArgumentException ie, WebRequest req) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> methodArgumentNotValidException(MethodArgumentNotValidException mnfe, WebRequest req) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), "Validation Error", mnfe.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    // Our custom exceptions


    @ExceptionHandler(IOException.class)
    public ResponseEntity<MyErrorDetails> ioException(IOException e, WebRequest req) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }



}
