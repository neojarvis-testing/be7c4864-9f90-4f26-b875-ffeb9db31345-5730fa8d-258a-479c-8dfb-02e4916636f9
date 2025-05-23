package com.examly.springappuser.dto;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String status;
    private String token;

    public LoginResponse(){}

   public LoginResponse(String status,String token){
    this.status = status;
    this.token = token;
   }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setToken(String token) {
        this.token = token;
    }

public String getToken() {
    return token;
}
    
}
