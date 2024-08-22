package com.example.demo.services.exceptions.UserExceptions;

public class IncompleteUserException extends  RuntimeException{
    public IncompleteUserException(String msg){
        super(msg);
    }
}
