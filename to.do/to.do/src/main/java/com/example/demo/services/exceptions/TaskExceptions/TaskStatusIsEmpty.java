package com.example.demo.services.exceptions.TaskExceptions;

public class TaskStatusIsEmpty extends RuntimeException {
    public TaskStatusIsEmpty(String msg){
        super(msg);
    }
}
