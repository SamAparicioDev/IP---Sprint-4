package com.example.demo.services.exceptions.TaskExceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String s) {
        super(s);
    }
}
