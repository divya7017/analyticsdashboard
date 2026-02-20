package com.example.analyticsdashboard.Controller;

import com.example.analyticsdashboard.Entity.User;
import com.example.analyticsdashboard.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerdUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        return userService.loginRequest(loginRequest);
    }
}
