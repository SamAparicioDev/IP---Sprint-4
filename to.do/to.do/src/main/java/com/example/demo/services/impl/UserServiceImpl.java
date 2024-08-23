package com.example.demo.services.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.mappers.UserEntityAndUserDTO;
import com.example.demo.models.UserRole;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.UserExceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityAndUserDTO userEntityAndUserDTO = new UserEntityAndUserDTO();


    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Not Found"));
        return userEntityAndUserDTO.userEntityToUserDTO(userEntity);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        validateUserNotEmptyFieldsInBody(userDTO);
        validateNotRepeatedFieldsInBody(userDTO);
        UserEntity userEntity = userRepository.save(userEntityAndUserDTO.userDTOToUserEntity(userDTO));
        return userEntityAndUserDTO.userEntityToUserDTO(userEntity);
    }
    @Override
    public void deleteUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Not Found"));
            userRepository.delete(userEntity);

    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
            validateUserNotEmptyFieldsInBody(userDTO);
             validateNotRepeatedFieldsInBody(userDTO);
            UserEntity user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setUsername(userDTO.getUsername());
            user.setRole(userDTO.getRoles());
            userRepository.save(user);
            return userEntityAndUserDTO.userEntityToUserDTO(user);

    }

    @Override
    public UserDTO changeRol(Long id, UserRole role){
        UserEntity userEntity = validateRolIsNotEmpty(id);
        if(userEntity.getRole().equals(role)){
            return userEntityAndUserDTO.userEntityToUserDTO(userEntity);
        }
            userEntity.setRole(role);
            userRepository.save(userEntity);
            return userEntityAndUserDTO.userEntityToUserDTO(userEntity);

    }
    public UserEntity validateRolIsNotEmpty(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        if(userEntity.getRole() == null){
            throw new UserRoleIsEmpty("Role field is empty");
        }
        return userEntity;
    }
    public void validateUserNotEmptyFieldsInBody(UserDTO userDTO){
        if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty()){
            throw new IncompleteUserException("Password field is empty");
        }
        if(userDTO.getUsername() == null || userDTO.getUsername().isEmpty()){
            throw new IncompleteUserException("Username field is empty");
        }
        if(userDTO.getEmail() == null || userDTO.getEmail().isEmpty()){
            throw new IncompleteUserException("Email field is empty");
        }
    }
    public void validateNotRepeatedFieldsInBody(UserDTO userDTO){
         List<UserEntity> messages = userRepository.findAll();
         for(UserEntity userEntity : messages){
             if(userEntity.getEmail().equals(userDTO.getEmail())){
                throw  new UserEmailAlreadyExistsException("Email already exists");
             }
             if(userEntity.getUsername().equals(userDTO.getUsername())){
                 throw  new UserNameAlreadyExistsException("Username already exists");
             }
         }
    }
}
