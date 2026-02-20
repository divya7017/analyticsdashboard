package com.example.analyticsdashboard.Configuration;

import com.example.analyticsdashboard.Entity.User;
import com.example.analyticsdashboard.Repo.UserRepo;
import com.example.analyticsdashboard.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader =  request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authheader != null && authheader.startsWith("Bearer ")) {
            token = authheader.substring(7);
            username = jwtService.getUsernameFromToken(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            Optional<User> optionalUser = userRepo.findByUsername(username);
            if(optionalUser.isPresent()){
                UsernamePasswordAuthenticationToken authtoken = new  UsernamePasswordAuthenticationToken(optionalUser.get().getUsername(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
