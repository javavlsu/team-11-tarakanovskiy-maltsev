package com.questions_platform.backend.util;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(Long id) {
        super("Group with id: " + id + " not found!");
    }
}
