package com.examly.apigateway.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.*;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono; 

@Component
public class JwtRequestFilter implements GatewayFilter{


    private final String SECRET_KEY = "mytestkey";
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    private final List<String> studentAccess = List.of("/api/loan/");
    private final List<String> loanManagerAccess = List.of("/api/loan/");
    private final List<String> adminAccess = List.of("/api/loan/");

  
    public Claims extractClaims(String token){

        return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    }
 
    public boolean isTokenExpired(String token){

        return extractClaims(token).getExpiration().before(new Date());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
         
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //validate path 
        if (!request.getHeaders().containsKey("Authorization")) {
            logger.error("Authorization Header Missing:{}",request.getPath());
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        else{
            String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);

            if(!authHeader.startsWith("Bearer ")){
                logger.error("Authorization header should start with Bearer:{}",authHeader);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            String jwtToken = authHeader.substring(7);

            Claims claims;

            try{
                claims = extractClaims(jwtToken);
                String username =  extractClaims(jwtToken).getSubject();
                String role = extractClaims(jwtToken).get("role",String.class);
                
               if(StringUtils.equals("STUDENT", role) && studentAccess.contains(request.getPath())){
                logger.info("STUDENT {} GRANTED for {}",username,role);
                 return chain.filter(exchange);
               }
               else if(StringUtils.equals("LOAN_MANAGER", role) && loanManagerAccess.contains(request.getPath())){
                logger.info("LOAN_MANAGER {} GRANTED for {}",username,role);
                return chain.filter(exchange);
               }
               else if(StringUtils.equals("ADMIN", role) && adminAccess.contains(request.getPath())){
               logger.info("STUDENT {} ADMIN GRANTED for {}",username,role);
               return chain.filter(exchange);
            }else{
                logger.info("UNAUTHORIZED user:{}, role:{}",username,role);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }   
            }catch(Exception e){
                logger.error("Invalid Token:{}, Error:{}",jwtToken,e.toString());
            }
            }
        }
    }
}
