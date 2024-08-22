package com.example.demo.services.exceptions.TaskExceptions;

public class IncompleteTaskException extends RuntimeException{
    public IncompleteTaskException(String msg) {
        super(msg);
    }

}
