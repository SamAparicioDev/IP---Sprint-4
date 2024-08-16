package com.example.demo.mappers;

import com.example.demo.dto.TaskDTO;
import com.example.demo.models.TaskEntity;

import java.util.HashSet;
import java.util.Set;

public class TaskEntityAndTaskDTO {

    public TaskDTO taskEntityToTaskDTO(TaskEntity taskEntity) {
         TaskDTO taskDTO = new TaskDTO();
         taskDTO.setId(taskEntity.getId());
         taskDTO.setDescription(taskEntity.getDescription());
         taskDTO.setStatus(taskEntity.getStatus());
         taskDTO.setTitle(taskEntity.getTitle());
         taskDTO.setUser_id(taskEntity.getUser_id());
         return taskDTO;
    }
    public Set<TaskDTO> taskEntityToTaskDTOSet(Set<TaskEntity> taskEntitySet) {
        Set<TaskDTO> taskDTOSet = new HashSet<>();
        for (TaskEntity taskEntity : taskEntitySet) {
            taskDTOSet.add(taskEntityToTaskDTO(taskEntity));
        }
        return taskDTOSet;
    }
    public Set<TaskEntity> taskDTOToTaskEntitySet(Set<TaskDTO> taskDTOSet) {
        Set<TaskEntity> taskEntitySet = new HashSet<>();
        for (TaskDTO taskDTO : taskDTOSet) {
            taskEntitySet.add(taskDTOToTaskEntity(taskDTO));
        }
        return taskEntitySet;
    }
    public TaskEntity taskDTOToTaskEntity(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setStatus(taskDTO.getStatus());
        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setUser_id(taskDTO.getUser_id());
        return taskEntity;
    }
}
