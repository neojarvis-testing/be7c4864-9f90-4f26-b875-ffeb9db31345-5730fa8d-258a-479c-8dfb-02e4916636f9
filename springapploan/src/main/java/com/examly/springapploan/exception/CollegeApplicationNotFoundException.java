package com.examly.springapploan.exception;

public class CollegeApplicationNotFoundException extends RuntimeException {

    public CollegeApplicationNotFoundException(int id){
        super("College Application Not Found with Id "+id);
    }

    public CollegeApplicationNotFoundException(String message){
        super(message);
    }
}
