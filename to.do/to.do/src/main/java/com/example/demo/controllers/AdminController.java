package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.Roles;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.UserNotFoundException;
import com.example.demo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/auth")
@RestController
public class AdminController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PutMapping("/admin/{id}")
    public ResponseEntity<UserDTO> convertToAdmin(@PathVariable Long id){
        UserDTO userDTO = userServiceImpl.getUserById(id);
        if(userDTO != null){
            userDTO.setRoles(Roles.ADMIN);
            userServiceImpl.updateUser(id,userDTO);
            return ResponseEntity.ok(userDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
