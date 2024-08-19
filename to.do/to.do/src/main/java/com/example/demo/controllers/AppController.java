package com.example.demo.controllers;

import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public UserEntity getUser(Authentication authentication){
        return userRepository.findByUsername(authentication.name()).orElseThrow( () -> new UsernameNotFoundException("No encontre nada"));
    }
}
