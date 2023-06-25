package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.StudentTest;
import com.questions_platform.backend.domain.TestAnswer;
import com.questions_platform.backend.domain.TestQuestion;
import com.questions_platform.backend.domain.User;
import com.questions_platform.backend.service.StudentTestService;
import com.questions_platform.backend.service.TestAnswerService;
import com.questions_platform.backend.service.TestQuestionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/test")
public class StudentTestController {
    private final StudentTestService studentTestService;
    private final TestQuestionService testQuestionService;
    private final TestAnswerService testAnswerService;

    public StudentTestController(StudentTestService studentTestService,
                                 TestQuestionService testQuestionService,
                                 TestAnswerService testAnswerService) {
        this.studentTestService = studentTestService;
        this.testQuestionService = testQuestionService;
        this.testAnswerService = testAnswerService;
    }

    @GetMapping("{disciplineId}")
    public String findAllTestByDiscipline(@PathVariable Long disciplineId, Model model){
        model.addAttribute("tests", studentTestService.findAllByDisciplineId(disciplineId));
        return "test";
    }

    @GetMapping("/available")
    public String findAvailableTests(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("tests", studentTestService.findAllAvailableTest(user.getId()));
        return "test";
    }

    @GetMapping("/passed")
    public String findPassedTests(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("tests", studentTestService.findAllPassedTest(user.getId()));
        return "passedTest";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/available/change/{testId}")
    public String changeAvailable(@PathVariable Long testId){
        studentTestService.changeTestAvailable(testId);
        return "redirect:/teacher";
    }

    @GetMapping("/question/{testId}")
    public String findAllQuestionByTest(@PathVariable Long testId, Model model){
        model.addAttribute("testId", testId);
        model.addAttribute("questions", testQuestionService.findAllByTestId(testId));
        return "question";
    }

    @GetMapping("/result/{testId}")
    public String findTestResult(@PathVariable Long testId,
                                 @AuthenticationPrincipal User user,
                                 Model model){
        model.addAttribute("result", studentTestService.findResult(testId, user.getId()));
        return "result";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/result/all/{testId}")
    public String findAllTestResults(@PathVariable Long testId, Model model){
        model.addAttribute("result", studentTestService.findAllResult(testId));
        return "result";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping
    public String addTest(StudentTest studentTest){
        studentTestService.saveWithDiscipline(studentTest);
        return "redirect:/teacher";
    }
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/question/{testId}")
    public String addQuestion(TestQuestion question, @PathVariable Long testId){
        testQuestionService.save(question, testId);
        return "redirect:/teacher";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/answers/{questionId}")
    public String addAnswers(TestAnswer answer, @PathVariable Long questionId){
        testAnswerService.save(answer, questionId);
        return "redirect:/teacher";
    }

    @PostMapping("/result/{testId}")
    public String saveTestResult(@RequestParam HashMap<String, String> answer,
                                 @PathVariable Long testId,
                                 @AuthenticationPrincipal User user){
        studentTestService.saveTestResult(answer, testId, user.getId());
        return "redirect:/";
    }
}
