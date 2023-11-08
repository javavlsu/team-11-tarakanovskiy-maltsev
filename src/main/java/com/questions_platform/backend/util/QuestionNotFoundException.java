package com.questions_platform.backend.util;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Long id) {
        super("Question with id: " + id + " not found!");
    }
}
