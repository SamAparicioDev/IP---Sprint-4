package com.example.demo.services.exceptions.UserExceptions;

public class UserNameAlreadyExistsException extends RuntimeException{
    public UserNameAlreadyExistsException(String msg){
        super(msg);
    }
}
