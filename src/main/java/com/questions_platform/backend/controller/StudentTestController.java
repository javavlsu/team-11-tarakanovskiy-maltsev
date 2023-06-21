package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.*;
import com.questions_platform.backend.dto.TestQuestionDto;
import com.questions_platform.backend.service.StudentTestService;
import com.questions_platform.backend.service.TestAnswerService;
import com.questions_platform.backend.service.TestQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
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
    public ResponseEntity<List<StudentTest>> findAllTestByDiscipline(@PathVariable Long disciplineId){
        List<StudentTest> tests = studentTestService.findAllByDisciplineId(disciplineId);
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/available")
    public ResponseEntity<List<StudentTest>> findAvailableTests(@AuthenticationPrincipal User user){
        List<StudentTest> tests = studentTestService.findAllAvailableTest(user.getId());
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/passed")
    public ResponseEntity<List<StudentTest>> findPassedTests(@AuthenticationPrincipal User user){
        List<StudentTest> tests = studentTestService.findAllPassedTest(user.getId());
        return ResponseEntity.ok(tests);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/available/change/{testId}")
    public ResponseEntity<HttpStatus> changeAvailable(@PathVariable Long testId){
        studentTestService.changeTestAvailable(testId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/question/{testId}")
    public ResponseEntity<List<TestQuestionDto>> findAllQuestionByTest(@PathVariable Long testId){
        List<TestQuestionDto> questions = testQuestionService.findAllByTestId(testId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/result/{testId}")
    public ResponseEntity<TestResult> findTestResult(@PathVariable Long testId,
                                                     @AuthenticationPrincipal User user){
        TestResult result = studentTestService.findResult(testId, user.getId());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/result/all/{testId}")
    public ResponseEntity<List<TestResult>> findAllTestResults(@PathVariable Long testId){
        List<TestResult> results = studentTestService.findAllResult(testId);
        return ResponseEntity.ok(results);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("{disciplineId}")
    public ResponseEntity<HttpStatus> addTest(@RequestBody StudentTest studentTest,
                               @PathVariable Long disciplineId){
        studentTestService.saveWithDiscipline(studentTest, disciplineId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/question/{testId}")
    public ResponseEntity<HttpStatus> addQuestion(@RequestBody TestQuestion question,
                                                  @PathVariable Long testId){
        testQuestionService.save(question, testId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/answers/{questionId}")
    public ResponseEntity<HttpStatus> addAnswers(@RequestBody List<TestAnswer> answers,
                                                 @PathVariable Long questionId){
        testAnswerService.saveAll(answers, questionId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/result/{testId}")
    public ResponseEntity<HttpStatus> saveTestResult(@RequestBody List<String> answer,
                                                     @PathVariable Long testId,
                                                     @AuthenticationPrincipal User user){
        studentTestService.saveTestResult(answer, testId, user.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
