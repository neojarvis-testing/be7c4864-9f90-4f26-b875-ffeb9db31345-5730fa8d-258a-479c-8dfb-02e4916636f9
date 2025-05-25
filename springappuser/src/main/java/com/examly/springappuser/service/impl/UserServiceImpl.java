package com.examly.springappuser.service.impl;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springappuser.config.JwtTokenGen;
import com.examly.springappuser.dto.LoginRequest;
import com.examly.springappuser.dto.LoginResponse;
import com.examly.springappuser.exceptions.InvalidCredintials;
import com.examly.springappuser.exceptions.UserExistsExeption;
import com.examly.springappuser.exceptions.UserNotFoundException;
import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepository;
import com.examly.springappuser.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    

    private UserRepository userRepository;
    private JwtTokenGen jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,JwtTokenGen jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
    

    public User registerUser(User user) throws UserExistsExeption{

        Optional<User> existingUserOptional = userRepository.findEmailAndUsername(user.getEmail(),user.getUsername());

            if(existingUserOptional.isPresent()){
                //throw exception saying user already exisit
                throw new UserExistsExeption("User Allready Exist !");
            }else{
                return userRepository.save(user);
            }

    }


    public LoginResponse loginUser(LoginRequest loginRequest) throws UserNotFoundException, InvalidCredintials{

    User user = userRepository.findByEmail(loginRequest.getEmail())
    .orElseThrow(()->new UserNotFoundException("User Not Found !"));

    if(StringUtils.equals(loginRequest.getPassword(), user.getPassword())){
        //generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getUserRole());
        //return response

        return LoginResponse.builder()
        .status("SUCCESS")
        .userId(user.getUserId())
        .email(user.getEmail())
        .mobileNumber(user.getMobileNumber())
        .userRole(user.getUserRole())
        .username(user.getUsername())
        .token(token).build(); 
         
    }else{
        throw new InvalidCredintials("Incorrect Password !");
    }

}

}
