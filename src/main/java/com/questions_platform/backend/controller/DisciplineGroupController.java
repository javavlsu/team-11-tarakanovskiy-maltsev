package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.Discipline;
import com.questions_platform.backend.domain.StudentGroup;
import com.questions_platform.backend.domain.User;
import com.questions_platform.backend.service.DisciplineGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discipline")
@CrossOrigin
public class DisciplineGroupController {
    private final DisciplineGroupService disciplineGroupService;

    public DisciplineGroupController(DisciplineGroupService disciplineGroupService) {
        this.disciplineGroupService = disciplineGroupService;
    }

    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<Discipline>> findAllDiscipline() {
        List<Discipline> disciplines = disciplineGroupService.findAllDiscipline();
        return ResponseEntity.ok(disciplines);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/group")
    public ResponseEntity<List<StudentGroup>> findAllGroup() {
        List<StudentGroup> groups = disciplineGroupService.findAllGroup();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/byGroup/{id}")
    public ResponseEntity<List<Discipline>> findAllDisciplineByGroup(@AuthenticationPrincipal User user) {
        List<Discipline> disciplines = disciplineGroupService.findByGroupId(user.getGroup().getId());
        return ResponseEntity.ok(disciplines);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<HttpStatus> addDiscipline(@RequestBody Discipline discipline) {
        disciplineGroupService.saveDiscipline(discipline);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/group")
    public ResponseEntity<HttpStatus> addGroup(@RequestBody StudentGroup group) {
        disciplineGroupService.saveGroup(group);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/group/{disciplineId}")
    public ResponseEntity<Discipline> addGroupForDiscipline(@PathVariable Long disciplineId,
                                                            @RequestParam("groupId") Long groupId) {
        Discipline discipline = disciplineGroupService.changeGroupDiscipline(disciplineId, groupId);
        return ResponseEntity.ok(discipline);
    }
}
