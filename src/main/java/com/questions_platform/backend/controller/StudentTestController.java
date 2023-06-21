package com.questions_platform.backend.controller;

import com.questions_platform.backend.domain.StudentTest;
import com.questions_platform.backend.domain.TestAnswer;
import com.questions_platform.backend.domain.TestQuestion;
import com.questions_platform.backend.domain.TestResult;
import com.questions_platform.backend.dto.TestQuestionDto;
import com.questions_platform.backend.service.StudentTestService;
import com.questions_platform.backend.service.TestAnswerService;
import com.questions_platform.backend.service.TestQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
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

    // добавить ResponseEntity<>

    @GetMapping("{disciplineId}")
    public List<StudentTest> findAllTestByDiscipline(@PathVariable Long disciplineId){
        return studentTestService.findAllByDisciplineId(disciplineId);
    }

    @GetMapping("/available/{testId}")
    public StudentTest changeAvailable(@PathVariable Long testId){
        return studentTestService.changeTestAvailable(testId);
    }

    @GetMapping("/question/{testId}")
    public List<TestQuestionDto> findAllQuestionByTest(@PathVariable Long testId){
        return testQuestionService.findAllByTestId(testId);
    }

    @GetMapping("/result/{testId}")
    public void findTestResult(@PathVariable Long testId){
        // добавить дто для результыта теста
    }

    @PostMapping
    public StudentTest createTest(@RequestBody StudentTest studentTest){
        return studentTestService.save(studentTest);
    }

    @PostMapping("/question")
    public TestQuestion addQuestion(@RequestBody TestQuestion question){
        return testQuestionService.save(question);
    }

    @PostMapping("/answers")
    public List<TestAnswer> addAnswers(@RequestBody List<TestAnswer> answers){
        return testAnswerService.saveAll(answers);
    }

    @PostMapping("/result/{testId}")
    public TestResult saveTestResult(@RequestBody List<String> answer, @PathVariable Long testId){
        return studentTestService.saveTestResult(answer, testId);
    }
}
