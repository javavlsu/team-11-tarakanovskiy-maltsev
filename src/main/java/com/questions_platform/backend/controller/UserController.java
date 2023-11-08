package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.User;
import com.questions_platform.backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("{userId}")
    public String addGroup(@PathVariable Long userId, @RequestParam Long groupId){
        userService.addGroup(userId, groupId);
        return "redirect:/admin";
    }

    @PostMapping("/student")
    public String createStudent(@ModelAttribute(name = "student") User user){
        userService.createStudent(user);
        return "redirect:/admin";
    }

    @PostMapping("/teacher")
    public String createTeacher(@ModelAttribute(name = "teacher") User user){
        userService.createTeacher(user);
        return "redirect:/admin";
    }

    @GetMapping("/add/{userId}/{groupId}")
    public String addStudentForGroup(@PathVariable Long userId, @PathVariable Long groupId){
        userService.addGroup(userId, groupId);
        return "redirect:/admin";
    }

    @PostMapping("/pass/{userId}")
    public String changePassword(@PathVariable Long userId, @RequestParam String password){
        userService.changePassword(password, userId);
        return "redirect:/admin";
    }
}
