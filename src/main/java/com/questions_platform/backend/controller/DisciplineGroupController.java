package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.Discipline;
import com.questions_platform.backend.service.DisciplineGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discipline")
public class DisciplineGroupController {
    private final DisciplineGroupService disciplineGroupService;

    public DisciplineGroupController(DisciplineGroupService disciplineGroupService) {
        this.disciplineGroupService = disciplineGroupService;
    }

    @GetMapping
    public ResponseEntity<List<Discipline>> findAllDiscipline(){
        List<Discipline> disciplines = disciplineGroupService.findAll();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("{id}")  // TODO доставать id группы из AuthenticationPrincipal
    public ResponseEntity<List<Discipline>> findAllDiscipline(@PathVariable Long id){
        List<Discipline> disciplines = disciplineGroupService.findByGroupId(id);
        return ResponseEntity.ok(disciplines);
    }

}
