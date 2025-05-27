package com.examly.springapploan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.examly.springapploan.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDTO> handleAuthException(AuthException e){
        return new ResponseEntity<>(new ErrorDTO(403,e.getMessage()),HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(LoanNotFoudException.class)
    public ResponseEntity<ErrorDTO> handleLaonNotFoundException(LoanNotFoudException exception){
        return new ResponseEntity<>(new ErrorDTO(400, exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CollegeNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCollegeNotFoundException(CollegeNotFoundException exception){
        return new ResponseEntity<>(new ErrorDTO(400, exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CollegeApplicationNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCollegeApplicationNotFoundException(CollegeApplicationNotFoundException exception){
        return new ResponseEntity<>(new ErrorDTO(400, exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> exception(Exception exception){
        return new ResponseEntity<>(new ErrorDTO(500, exception.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
