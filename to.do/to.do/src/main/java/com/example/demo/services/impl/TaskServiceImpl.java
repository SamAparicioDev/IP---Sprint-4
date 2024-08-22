package com.example.demo.services.impl;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.enums.TaskStatusEnum;
import com.example.demo.mappers.TaskEntityAndTaskDTO;
import com.example.demo.mappers.UserEntityAndUserDTO;
import com.example.demo.models.TaskEntity;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TaskService;
import com.example.demo.services.exceptions.TaskExceptions.IncompleteTaskException;
import com.example.demo.services.exceptions.TaskExceptions.TaskNotFoundException;
import com.example.demo.services.exceptions.UserExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    private TaskEntityAndTaskDTO taskEntityAndTaskDTO = new TaskEntityAndTaskDTO();

    @Autowired
    private UserRepository userRepository;

    private UserEntityAndUserDTO userEntityAndUserDTO = new UserEntityAndUserDTO();

    @Override
    public TaskDTO createTask(TaskDTO task) {
        if(!validateTaskBody(task).equals("")){
            throw new IncompleteTaskException(validateTaskBody(task));
        }
        TaskEntity taskEntity = taskRepository.save(taskEntityAndTaskDTO.taskDTOToTaskEntity(task));
        UserEntity userEntity = userRepository.findById(taskEntity.getUser_id().getId()).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        taskEntity.setUser_id(userEntity);
        return taskEntityAndTaskDTO.taskEntityToTaskDTO(taskEntity);
    }


    @Override
    public TaskDTO getTaskById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
        UserEntity userEntity = userRepository.findById(taskEntity.getUser_id().getId()).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        taskEntity.setUser_id(userEntity);
        return taskEntityAndTaskDTO.taskEntityToTaskDTO(taskEntity);
    }
    @Override
    public void deleteTaskById(Long id){
         TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task Not Found"));
         taskRepository.delete(taskEntity);
    }

    @Override
    public TaskDTO updateTask(Long id,TaskDTO task) {
        if(!validateTaskBody(task).equals("")){
            throw new IncompleteTaskException(validateTaskBody(task));
        }
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
        taskEntity.setDescription(task.getDescription());
        taskEntity.setUser_id(task.getUser_id());
        taskEntity.setStatus(task.getStatus());
        taskEntity.setTitle(task.getTitle());
        UserEntity userEntity = userRepository.findById(task.getUser_id().getId()).orElseThrow(()->new UserNotFoundException("User Not Found"));
        taskEntity.setUser_id(userEntity);
        return taskEntityAndTaskDTO.taskEntityToTaskDTO(taskEntity);
        }

    @Override
    public TaskDTO changeStatus(Long id, TaskStatusEnum status) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task Not Found"));
        if(taskEntity.getStatus() == null){
            throw new IncompleteTaskException("Status field is empty");
        }
        if(taskEntity.getStatus().equals(status)){
             return taskEntityAndTaskDTO.taskEntityToTaskDTO(taskEntity);
         }
         taskEntity.setStatus(status);
         taskRepository.save(taskEntity);
         return taskEntityAndTaskDTO.taskEntityToTaskDTO(taskEntity);
    }
    public String validateTaskBody(TaskDTO task) {
        if(task.getTitle().isEmpty()  || task.getTitle().isBlank()){
            return  "Title field is empty";
        }
        if(task.getDescription().isEmpty()  || task.getDescription().isBlank()){
            return "Description field is empty";
        }
        if(task.getStatus()  == null){
            return  "Status field is empty";
        }
        else{
            return "";
        }
    }

}

