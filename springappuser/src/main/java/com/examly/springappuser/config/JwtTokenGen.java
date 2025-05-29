package com.examly.springappuser.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGen {

    private final String SECRET_KEY = "mytestkey";

    public String generateToken(String username,String role, long userId){
        Map<String,Object> claims = new HashMap<>(); 
        claims.put("userId", username); 
        claims.put("role", role);
        claims.put("userName", userId);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims,String subject){
        return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject) 
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*36000))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
    }

    
    public Claims extractClaims(String token){
        return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    }

    public String getUserName(String jwtToken){
        return extractClaims(jwtToken).getSubject();
    }
    public String getUserRole(String jwtToken){
        return extractClaims(jwtToken).get("role",String.class);
    }
    public String getUserId(String jwtToken){
        return extractClaims(jwtToken).get("userId",String.class);
    }
    public long getUserIdToken(String jwtToken){
        return extractClaims(jwtToken).get("userName",Long.class);
    }
    
    public boolean isTokenExpired(String token){

        return extractClaims(token).getExpiration().before(new Date());
    }
}
