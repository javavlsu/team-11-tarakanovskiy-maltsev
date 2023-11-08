package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.Discipline;
import com.questions_platform.backend.domain.StudentGroup;
import com.questions_platform.backend.domain.User;
import com.questions_platform.backend.service.DisciplineGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/discipline")
public class DisciplineGroupController {
    private final DisciplineGroupService disciplineGroupService;

    public DisciplineGroupController(DisciplineGroupService disciplineGroupService) {
        this.disciplineGroupService = disciplineGroupService;
    }

    @GetMapping("/byGroup")
    public String findAllDisciplineByGroup(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("disciplines", disciplineGroupService.findByGroupId(user.getGroup().getId()));
        return "discipline";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String addDiscipline(Discipline discipline) {
        disciplineGroupService.saveDiscipline(discipline);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/group")
    public String addGroup(StudentGroup group) {
        disciplineGroupService.saveGroup(group);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/group/{disciplineId}/{groupId}")
    public String addGroupForDiscipline(@PathVariable Long disciplineId, @PathVariable Long groupId) {
        disciplineGroupService.changeGroupDiscipline(disciplineId, groupId);
        return "redirect:/teacher";
    }
}
