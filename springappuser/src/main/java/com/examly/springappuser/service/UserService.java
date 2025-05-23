package com.examly.springappuser.service;

import com.examly.springappuser.dto.LoginRequest;
import com.examly.springappuser.dto.LoginResponse;
import com.examly.springappuser.model.User;

public interface UserService {
 
User registerUser(User user);
LoginResponse loginUser(LoginRequest loginRequest);
    
}
