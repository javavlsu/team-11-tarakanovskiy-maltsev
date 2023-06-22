package com.questions_platform.backend.util;

public class DisciplineNotFoundException extends RuntimeException{
    public DisciplineNotFoundException(Long id) {
        super("Discipline with id: " + id + " not found!");
    }
}
