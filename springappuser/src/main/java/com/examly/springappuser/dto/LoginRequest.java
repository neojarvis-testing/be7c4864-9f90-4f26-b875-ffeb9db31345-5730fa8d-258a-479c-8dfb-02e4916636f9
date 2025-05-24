package com.examly.springappuser.dto;

import java.io.Serializable; 

public class LoginRequest implements Serializable {

    String email;
    String password;
    
    public LoginRequest(){}

    public LoginRequest(String email,String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
    return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

}
