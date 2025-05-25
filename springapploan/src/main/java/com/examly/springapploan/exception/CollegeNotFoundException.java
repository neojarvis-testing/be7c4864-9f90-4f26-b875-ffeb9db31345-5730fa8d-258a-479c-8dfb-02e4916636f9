package com.examly.springapploan.exception;

public class CollegeNotFoundException extends RuntimeException {

    public CollegeNotFoundException(long loanId){
        super("College not found with Id "+loanId);
    }

    public CollegeNotFoundException(String message){
        super(message);
    }
    
}
