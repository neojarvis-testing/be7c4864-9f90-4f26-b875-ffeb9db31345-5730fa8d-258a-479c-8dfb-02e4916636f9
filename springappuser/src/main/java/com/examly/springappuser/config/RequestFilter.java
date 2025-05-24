package com.examly.springappuser.config;

import java.io.IOException;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.examly.springappuser.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 

@Component
public class RequestFilter extends OncePerRequestFilter {


private JwtUtil jwtUtil;

@Autowired
public RequestFilter(JwtUtil jwtUtil){
    this.jwtUtil = jwtUtil;
}


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
                final String authHeader = request.getHeader("Authorization");
               
                String jwt = null;
            if (authHeader!=null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
                
                if(jwtUtil.isTokenExpired(jwt)){
                    String username = jwtUtil.getUserName(jwt);
                    String role = jwtUtil.getUserRole(jwt);

                    List<GrantedAuthority> authList = List.of(new SimpleGrantedAuthority(role));
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,null,authList);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            chain.doFilter(request,response);
    }
    
}
