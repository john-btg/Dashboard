package com.example.demo.Auth.exceptions;


public class UserNotEnabled extends RuntimeException {
    public UserNotEnabled(String message)
    {
        super(message);
    }
}
