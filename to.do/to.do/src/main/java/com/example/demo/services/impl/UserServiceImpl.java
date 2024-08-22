package com.example.demo.services.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.mappers.UserEntityAndUserDTO;
import com.example.demo.models.UserRole;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.UserExceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserEntityAndUserDTO userEntityAndUserDTO = new UserEntityAndUserDTO();


    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Not Found"));
        return userEntityAndUserDTO.userEntityToUserDTO(userEntity);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(!validateUserNotEmptyFieldsInBody(userDTO).equals("")){
            throw new IncompleteUserException(validateUserNotEmptyFieldsInBody(userDTO));
        }
        if(!validateNotRepeatedFieldsInBody(userDTO).equals("")){
            if(validateUserNotEmptyFieldsInBody(userDTO).contains("Email")){
                throw new UserEmailAlreadyExistsException("Email Already Exists");
            }
            if(validateNotRepeatedFieldsInBody(userDTO).contains("Username")){
                throw new UserNameAlreadyExistsException("Username Already Exists");
            }
        }
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
            if(!validateUserNotEmptyFieldsInBody(userDTO).equalsIgnoreCase("")){
                throw new IncompleteUserException(validateUserNotEmptyFieldsInBody(userDTO));
            }
            if(!validateNotRepeatedFieldsInBody(userDTO).equals("")){
                if(validateUserNotEmptyFieldsInBody(userDTO).contains("Email")){
                    throw new UserEmailAlreadyExistsException("Email Already Exists");
                }
                if(validateUserNotEmptyFieldsInBody(userDTO).contains("Username")){
                    throw new UserNameAlreadyExistsException("Username Already Exists");
                }
            }
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
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        if(userEntity.getRole() == null){
            throw new UserRoleIsEmpty("Role field is empty");
        }
        if(userEntity.getRole().equals(role)){
            return userEntityAndUserDTO.userEntityToUserDTO(userEntity);
        }
            userEntity.setRole(role);
            userRepository.save(userEntity);
            return userEntityAndUserDTO.userEntityToUserDTO(userEntity);

    }

    public String validateUserNotEmptyFieldsInBody(UserDTO userDTO){
        if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty()){
            return "Password field is empty";
        }
        if(userDTO.getUsername() == null || userDTO.getUsername().isEmpty()){
            return "Username field is empty";
        }
        if(userDTO.getEmail() == null || userDTO.getEmail().isEmpty()){
            return "Email field is empty";
        }
        else{
            return "";
        }
    }
    public String validateNotRepeatedFieldsInBody(UserDTO userDTO){
         List<String> messages = userRepository.findAll().stream()
                         .flatMap((userEntity) -> {
                             List<String> errors = List.of();
                             if(userDTO.getUsername().equals(userEntity.getUsername())){
                                 errors = List.of("Username is repeated");

                             }
                             if(userDTO.getEmail().equals(userEntity.getEmail())){
                                 errors = List.of("Email is repeated");

                             }
                             return errors.stream();
                         }).collect(Collectors.toList());
        return messages.isEmpty() ? "" : String.join(", ", messages);

    }
}
