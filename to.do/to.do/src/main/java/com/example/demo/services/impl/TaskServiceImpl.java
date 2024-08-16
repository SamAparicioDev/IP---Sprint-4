package com.example.demo.services.impl;

import com.example.demo.dto.TaskDTO;
import com.example.demo.mappers.TaskEntityAndTaskDTO;
import com.example.demo.models.TaskEntity;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.TaskService;
import com.example.demo.services.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    private TaskEntityAndTaskDTO taskEntityAndTaskDTO = new TaskEntityAndTaskDTO();

    @Override
    public TaskDTO createTask(TaskDTO task) {
        TaskEntity taskEntity = taskRepository.save(taskEntityAndTaskDTO.taskDTOToTaskEntity(task));

        return taskEntityAndTaskDTO.taskEntityToTaskDTO(taskEntity);
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("No se ha encontrado la tarea"));
        return taskEntityAndTaskDTO.taskEntityToTaskDTO(taskEntity);
    }
    @Override
    public void deleteTaskById(Long id){
         taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO updateTask(Long id,TaskDTO task) {
        if(taskRepository.existsById(id)){
            TaskDTO taskDTOOlder = taskEntityAndTaskDTO.taskEntityToTaskDTO(taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("No se ha encontrado la tarea")));
            taskDTOOlder.setDescription(task.getDescription());
            taskDTOOlder.setUser_id(task.getUser_id());
            taskDTOOlder.setStatus(task.getStatus());
            taskDTOOlder.setTitle(task.getTitle());
            return taskDTOOlder;
        }
        else{
             return null;
        }
    }
}
