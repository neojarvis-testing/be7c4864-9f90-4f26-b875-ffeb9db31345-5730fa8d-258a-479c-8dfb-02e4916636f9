package com.examly.springappuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springappuser.dto.LoginRequest;
import com.examly.springappuser.dto.LoginResponse;
import com.examly.springappuser.exceptions.InvalidCredintials;
import com.examly.springappuser.exceptions.UserExistsExeption;
import com.examly.springappuser.exceptions.UserNotFoundException;
import com.examly.springappuser.model.User;
import com.examly.springappuser.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping(path ="/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User user)  throws UserExistsExeption{

        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
    @PostMapping(path ="/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> registerUserEntity(@RequestBody LoginRequest user)  throws UserNotFoundException,InvalidCredintials{
 
        return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
    }
}
