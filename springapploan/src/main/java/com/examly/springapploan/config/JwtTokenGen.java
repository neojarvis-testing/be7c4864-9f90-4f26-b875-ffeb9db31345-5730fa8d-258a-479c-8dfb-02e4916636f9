package com.examly.springapploan.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.examly.springapploan.exception.*;
 

public class JwtTokenGen {

    private static final String SECRET_KEY = "mytestkey";

    public static String generateToken(String username,String role){
        Map<String,Object> claims = new HashMap<>(); 
        claims.put("userId", role); 
        claims.put("role", role);
        return createToken(claims, username);
    }

    private static String createToken(Map<String, Object> claims,String subject){
        return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject) 
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*36000))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
    }

    
    private static Claims extractClaims(String token) throws AuthException{
        try{
        return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token.substring(7))
        .getBody();}
        catch(Exception e){
            throw new AuthException("Invalid Token");
        }
    }

    public static String getUserName(String jwtToken) throws AuthException{
    
        return extractClaims(jwtToken).getSubject();
    }

    public static String getUserRole(String jwtToken) throws AuthException{
        return extractClaims(jwtToken).get("role",String.class);
    }
    
    public static String getUserId(String jwtToken) throws AuthException{
        
        return extractClaims(jwtToken).get("userId",String.class);
    }
 
    public boolean isTokenExpired(String token) throws AuthException{

        return extractClaims(token).getExpiration().before(new Date());
    }
}
