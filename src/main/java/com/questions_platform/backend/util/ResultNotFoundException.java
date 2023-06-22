package com.questions_platform.backend.util;

public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(Long id) {
        super("Result by test with id: " + id + " not found!");
    }
}
