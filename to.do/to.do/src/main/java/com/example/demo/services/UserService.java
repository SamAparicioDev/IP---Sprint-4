package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.UserEntity;
import org.springframework.stereotype.Service;

public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO user);
    void deleteUserById(Long id);
    UserDTO updateUser(Long id,UserDTO user);
}
