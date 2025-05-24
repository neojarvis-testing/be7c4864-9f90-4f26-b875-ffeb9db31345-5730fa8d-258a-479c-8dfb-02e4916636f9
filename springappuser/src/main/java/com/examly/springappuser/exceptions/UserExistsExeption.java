package com.examly.springappuser.exceptions;

public class UserExistsExeption extends Exception{
    
    public UserExistsExeption(String message){
        super(message);
    }
}
