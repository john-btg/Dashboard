package com.example.demo.Auth.exceptions;


public class UserAlreadyRegistered extends RuntimeException {
    public UserAlreadyRegistered(String message)
    {
        super(message);
    }
}
