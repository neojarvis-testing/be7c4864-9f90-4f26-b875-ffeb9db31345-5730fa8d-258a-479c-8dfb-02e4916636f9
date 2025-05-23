package com.examly.springappuser.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepository;
import com.examly.springappuser.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){

        Optional<User> existingUserOptional = userRepository.findEmailAndUsername(user.getEmail(),user.getUsername());

            if(existingUserOptional.isPresent()){
                //throw exception saying user already exisit
                throw new RuntimeException("User Allready Exist !");
            }else{
                return userRepository.save(user);
            }

    }

}
