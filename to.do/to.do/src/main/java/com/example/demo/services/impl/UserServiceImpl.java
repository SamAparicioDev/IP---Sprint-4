package com.example.demo.services.impl;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.mappers.TaskEntityAndTaskDTO;
import com.example.demo.mappers.UserEntityAndUserDTO;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserDTO createUser(UserDTO user) {
        UserEntity userEntity = userRepository.save(userEntityAndUserDTO.userDTOToUserEntity(user));
        return userEntityAndUserDTO.userEntityToUserDTO(userEntity);
    }
    @Override
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Long id,UserDTO userDTO) {
        if(userRepository.existsById(id)){
            userRepository.save(userEntityAndUserDTO.userDTOToUserEntity(userDTO));
            return userDTO;
        }
        else{
            return null;
        }
    }
}
