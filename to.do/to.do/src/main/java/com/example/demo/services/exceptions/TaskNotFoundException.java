package com.example.demo.services.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String s) {
        super(s);
    }
}
