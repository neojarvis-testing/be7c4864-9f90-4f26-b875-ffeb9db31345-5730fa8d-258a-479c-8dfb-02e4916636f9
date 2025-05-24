package com.examly.springappuser.service;

import com.examly.springappuser.dto.LoginRequest;
import com.examly.springappuser.dto.LoginResponse;
import com.examly.springappuser.exceptions.InvalidCredintials;
import com.examly.springappuser.exceptions.UserExistsExeption;
import com.examly.springappuser.exceptions.UserNotFoundException;
import com.examly.springappuser.model.User;

public interface UserService {
 
User registerUser(User user)  throws UserExistsExeption;
LoginResponse loginUser(LoginRequest loginRequest)  throws UserNotFoundException,InvalidCredintials;
    
}
