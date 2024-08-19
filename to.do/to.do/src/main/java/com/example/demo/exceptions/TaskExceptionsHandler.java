package com.example.demo.exceptions;

import com.example.demo.services.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionsHandler extends RuntimeException {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> taskNotFound(TaskNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
