package com.example.demo.services;

import com.example.demo.dto.TaskDTO;
import com.example.demo.enums.TaskStatusEnum;

public interface TaskService {
    TaskDTO getTaskById(Long id);
    TaskDTO createTask(TaskDTO task);
    void deleteTaskById(Long id);
    TaskDTO updateTask(Long id,TaskDTO task);
    TaskDTO changeStatus(Long id, TaskStatusEnum status);
}
