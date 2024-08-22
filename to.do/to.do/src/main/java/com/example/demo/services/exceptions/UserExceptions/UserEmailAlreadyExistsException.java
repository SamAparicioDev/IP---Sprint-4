package com.example.demo.services.exceptions.UserExceptions;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
