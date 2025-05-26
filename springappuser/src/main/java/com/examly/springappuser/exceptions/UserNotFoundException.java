package com.examly.springappuser.exceptions;

public class UserNotFoundException extends Exception{
    
    public UserNotFoundException(String message){
        super(message);
    }
}
