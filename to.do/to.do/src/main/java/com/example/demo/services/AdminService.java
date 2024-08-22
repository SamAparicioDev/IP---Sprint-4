package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<UserDTO> convertToAdmin(Long id);
}
