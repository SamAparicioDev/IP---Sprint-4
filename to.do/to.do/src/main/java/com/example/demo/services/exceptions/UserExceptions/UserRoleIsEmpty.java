package com.example.demo.services.exceptions.UserExceptions;

public class UserRoleIsEmpty extends   RuntimeException{
    public UserRoleIsEmpty(String msg){
        super(msg);
    }
}
