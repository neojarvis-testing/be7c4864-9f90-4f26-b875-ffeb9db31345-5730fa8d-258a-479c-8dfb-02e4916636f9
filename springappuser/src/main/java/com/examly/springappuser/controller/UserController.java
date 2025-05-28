package com.examly.springappuser.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.*;
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
import com.examly.springappuser.exceptions.InvalidInput;
import com.examly.springappuser.exceptions.UserExistsExeption;
import com.examly.springappuser.exceptions.UserNotFoundException;
import com.examly.springappuser.model.User;
import com.examly.springappuser.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private List<String> allowedRoles = List.of("loanManager","admin","student");

    @PostMapping(path ="/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User user)  throws UserExistsExeption, InvalidInput{
        if(StringUtils.isEmpty(user.getEmail())||
        StringUtils.isEmpty(user.getPassword())){
            throw new InvalidInput("Invalid Input: "+user);
        }
        if(StringUtils.isEmpty(user.getUserRole())){
            throw new InvalidInput("Invalid Input: "+user);
        }
        if(!allowedRoles.contains(user.getUserRole().toLowerCase())){
            throw new InvalidInput("Invalid User Type: "+user.getUserRole());
        }
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
    @PostMapping(path ="/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> registerUserEntity(@RequestBody LoginRequest user)  throws UserNotFoundException,InvalidCredintials{
         logger.info("email :{}, Password: {}",user.getEmail(),user.getPassword());
        return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
    }
}
