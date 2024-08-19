package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userServiceImpl.getUserById(id));
    }
    @PostMapping("/save")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        return ResponseEntity.ok().body(userServiceImpl.createUser(user));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        if (userServiceImpl.getUserById(id) != null) {
            userServiceImpl.getUserById(id);
            return ResponseEntity.ok("Se elimino correctamente");
        } else {
            return ResponseEntity.badRequest().body("El archivo ya se encuentra eliminado o no existe");
        }
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        if(userServiceImpl.getUserById(id) != null) {

        }
        userServiceImpl.updateUser(id,userDTO);
        return ResponseEntity.ok(userServiceImpl.getUserById(id));
    }

}
