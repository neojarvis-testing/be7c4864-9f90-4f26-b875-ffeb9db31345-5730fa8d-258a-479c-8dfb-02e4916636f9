package com.examly.springappuser.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {

    private String status;
    private String token;
    private Long userId;
    private String email; 
    private String username;
    private String mobileNumber;
    private String userRole;

}
