package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.User;
import com.questions_platform.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{userId}")
    public ResponseEntity<HttpStatus> addGroup(@PathVariable Long userId,
                                               @RequestParam Long groupId){
        userService.addGroup(userId, groupId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/student")
    public ResponseEntity<HttpStatus> createStudent(User user){
        userService.createStudent(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/teacher")
    public ResponseEntity<HttpStatus> createTeacher(User user){
        userService.createTeacher(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/pass/{userId}")
    public ResponseEntity<HttpStatus> changePassword(@PathVariable Long userId,
                                                     @RequestBody String password){
        userService.changePassword(password, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
