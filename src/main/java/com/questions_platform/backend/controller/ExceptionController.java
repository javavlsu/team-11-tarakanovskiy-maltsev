package com.questions_platform.backend.controller;

import com.questions_platform.backend.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {DisciplineNotFoundException.class,
            GroupNotFoundException.class,
            QuestionNotFoundException.class,
            ResultNotFoundException.class,
            TestNotFoundException.class,
            UserNotFoundException.class})
    public ResponseEntity<String> notFoundException(Exception e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
