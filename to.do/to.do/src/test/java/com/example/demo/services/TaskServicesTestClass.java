package com.example.demo.services;

import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.exceptions.TaskExceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TaskServicesTestClass {
    @Autowired
    private TaskService taskService;
     @MockBean
    private TaskRepository taskRepository;

      @Test
    public void getTaskByIdTaskNotFoundTest(){
          assertThrows(TaskNotFoundException.class, () ->
              taskService.getTaskById(1L),"Task Not Found");
      }
}
