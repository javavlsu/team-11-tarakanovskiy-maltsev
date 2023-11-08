package com.questions_platform.backend.util;

public class TestNotFoundException extends RuntimeException{
    public TestNotFoundException(Long id) {
        super("Test with id: " + id + " not found!");
    }
}
