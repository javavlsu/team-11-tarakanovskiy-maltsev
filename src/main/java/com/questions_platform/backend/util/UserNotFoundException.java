package com.questions_platform.backend.util;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("User with id: " + id + "not found!");
    }
}
