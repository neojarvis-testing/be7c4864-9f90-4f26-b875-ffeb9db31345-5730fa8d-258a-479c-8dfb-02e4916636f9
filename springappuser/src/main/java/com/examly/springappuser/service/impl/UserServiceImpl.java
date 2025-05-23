package com.examly.springappuser.service.impl;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springappuser.dto.LoginRequest;
import com.examly.springappuser.dto.LoginResponse;
import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepository;
import com.examly.springappuser.service.UserService;
import com.examly.springappuser.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService{
    

    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
    

    public User registerUser(User user){

        Optional<User> existingUserOptional = userRepository.findEmailAndUsername(user.getEmail(),user.getUsername());

            if(existingUserOptional.isPresent()){
                //throw exception saying user already exisit
                throw new RuntimeException("User Allready Exist !");
            }else{
                return userRepository.save(user);
            }

    }


    public LoginResponse loginUser(LoginRequest loginRequest){

    User user = userRepository.findByEmail(loginRequest.getEmail())
    .orElseThrow(()->new RuntimeException("User Not Found !"));

    if(StringUtils.equals(loginRequest.getPassword(), user.getPassword())){
        //generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getUserRole());
        //return response
        return new LoginResponse("SUCCESS", token);
    }else{
        throw new RuntimeException("Incorrect Password !");
    }

}

}
