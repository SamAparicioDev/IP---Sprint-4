package com.example.demo.services;

import com.example.demo.models.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> authenticateUser( LoginRequest loginRequest);
}
