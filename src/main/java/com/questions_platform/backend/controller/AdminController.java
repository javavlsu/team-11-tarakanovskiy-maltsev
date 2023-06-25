package com.questions_platform.backend.controller;

import com.questions_platform.backend.service.DisciplineGroupService;
import com.questions_platform.backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final DisciplineGroupService disciplineGroupService;
    private final UserService userService;

    public AdminController(DisciplineGroupService disciplineGroupService, UserService userService) {
        this.disciplineGroupService = disciplineGroupService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("disciplines", disciplineGroupService.findAllDiscipline());
        model.addAttribute("groups", disciplineGroupService.findAllGroup());
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("{id}")
    public String addStudentForGroup(Model model, @PathVariable Long id){
        model.addAttribute("groups", disciplineGroupService.findAllGroup());
        model.addAttribute("userId", id);
        return "addGroupDiscipline";
    }
}
