package com.questions_platform.backend.controller;

import com.questions_platform.backend.service.DisciplineGroupService;
import com.questions_platform.backend.service.StudentTestService;
import com.questions_platform.backend.service.TestAnswerService;
import com.questions_platform.backend.service.TestQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private final DisciplineGroupService disciplineGroupService;
    private final StudentTestService testService;
    private final TestQuestionService questionService;
    private final TestAnswerService answerService;

    public TeacherController(DisciplineGroupService disciplineGroupService, StudentTestService testService, TestQuestionService questionService, TestAnswerService answerService) {
        this.disciplineGroupService = disciplineGroupService;
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("disciplines", disciplineGroupService.findAllDiscipline());
        model.addAttribute("groups", disciplineGroupService.findAllGroup());
        model.addAttribute("tests", testService.findAll());
        return "teacher";
    }

    @GetMapping("{testId}")
    public String test(Model model, @PathVariable Long testId) {
        model.addAttribute("questions", questionService.findByTestId(testId));
        model.addAttribute("testId", testId);
        return "addQuestion";
    }

    @GetMapping("/question/{questionId}")
    public String quest(Model model, @PathVariable Long questionId) {
        model.addAttribute("answers", answerService.findAllById(questionId));
        model.addAttribute("questionId", questionId);
        return "addAnswer";
    }

    @GetMapping("/group/{id}")
    public String addGroup(Model model, @PathVariable Long id){
        model.addAttribute("groups", disciplineGroupService.findAllGroup());
        model.addAttribute("disciplineId", id);
        return "addGroupStudent";
    }
}
