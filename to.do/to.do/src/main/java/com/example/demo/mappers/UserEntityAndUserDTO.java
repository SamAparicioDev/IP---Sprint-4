package com.example.demo.mappers;


import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.TaskEntity;
import com.example.demo.models.UserEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserEntityAndUserDTO {

    TaskEntityAndTaskDTO taskEntityAndTaskDTO = new TaskEntityAndTaskDTO();

    public UserDTO userEntityToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setTask(taskEntityAndTaskDTO.taskEntityToTaskDTOSet(userEntity.getTask()));
        userDTO.setRoles(userEntity.getRole());
        return userDTO;
    }
    public UserEntity userDTOToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setTask(taskEntityAndTaskDTO.taskDTOToTaskEntitySet(userDTO.getTask()));
        userEntity.setRole(userDTO.getRoles());
        return userEntity;
    }
}
