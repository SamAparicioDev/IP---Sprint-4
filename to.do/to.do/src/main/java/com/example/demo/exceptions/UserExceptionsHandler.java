package com.example.demo.exceptions;

import com.example.demo.services.exceptions.UserExceptions.IncompleteUserException;
import com.example.demo.services.exceptions.UserExceptions.UserNotFoundException;

import com.example.demo.services.exceptions.UserExceptions.UserRoleIsEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionsHandler extends RuntimeException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(IncompleteUserException.class)
    public ResponseEntity<String> handleIncompleteUserException(IncompleteUserException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(UserRoleIsEmpty.class)
    public ResponseEntity<String> handleUserRoleIsEmptyException(UserRoleIsEmpty e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
