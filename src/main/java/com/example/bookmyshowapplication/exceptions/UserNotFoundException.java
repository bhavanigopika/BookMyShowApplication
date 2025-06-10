package com.example.bookmyshowapplication.exceptions;

//Should I extend Exception or RunTimeException? Both works because both are from throwable
//It's all about checked and unchecked exception
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
