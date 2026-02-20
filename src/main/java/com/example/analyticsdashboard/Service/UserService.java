package com.example.analyticsdashboard.Service;


import com.example.analyticsdashboard.Entity.User;
import com.example.analyticsdashboard.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String registerdUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepo.findByUsername(user.getUsername()).isPresent()) {
            return "username already taken";
        }
        userRepo.save(user);
        return "Register Successful";
    }

    public String loginRequest(User loginRequest) {
        Optional<User> optionalUser = userRepo.findByUsername(loginRequest.getUsername());

        if(optionalUser.isEmpty()){
            return "user not found";
        }

        User user = optionalUser.get();

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return "invalid password";
        }


        return jwtService.generateToken(loginRequest.getUsername());
    }
}
