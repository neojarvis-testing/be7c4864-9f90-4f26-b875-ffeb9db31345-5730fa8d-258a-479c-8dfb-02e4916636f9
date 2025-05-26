package com.examly.springappuser.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.examly.springappuser.model.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>(new ErrorDto(400, "User Not Found !"),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserExistsExeption.class)
    public ResponseEntity<?> handleUserExistsExeption(UserExistsExeption exception){
        return new ResponseEntity<>(new ErrorDto(400, "User Already Exists !"),HttpStatus.OK);
    }
    @ExceptionHandler(InvalidCredintials.class)
    public ResponseEntity<?> invalidCredintialsExeption(InvalidCredintials exception){
        return new ResponseEntity<>(new ErrorDto(400, "Incorrect Password !"),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception exception){
        return new ResponseEntity<>(new ErrorDto(500, exception.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
