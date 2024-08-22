package com.example.demo.controllers;


import com.example.demo.jwtconfigurations.JwtUtils;
import com.example.demo.models.LoginRequest;
import com.example.demo.services.impl.AuthenticationManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManagerImpl authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
            String jwt = authenticationManager.authenticate(loginRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(jwt);

    }
}