package com.example.demo.exceptions;

import com.example.demo.services.exceptions.TaskExceptions.IncompleteTaskException;
import com.example.demo.services.exceptions.TaskExceptions.TaskNotFoundException;
import com.example.demo.services.exceptions.TaskExceptions.TaskStatusIsEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionsHandler extends RuntimeException {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(IncompleteTaskException.class)
    public ResponseEntity<String> handleIncompleteTaskException(IncompleteTaskException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(TaskStatusIsEmpty.class)
    public ResponseEntity<String> handleIncompleteTaskException(TaskStatusIsEmpty e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
