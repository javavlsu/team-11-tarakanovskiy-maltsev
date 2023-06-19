package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.StudentTest;
import com.questions_platform.backend.domain.TestResult;
import com.questions_platform.backend.repository.StudentTestRepository;
import com.questions_platform.backend.repository.TestResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTestService {
    private final StudentTestRepository testRepository;
    private final TestResultRepository resultRepository;
    private final TestQuestionService questionService;

    public StudentTestService(StudentTestRepository testRepository,
                              TestResultRepository resultRepository,
                              TestQuestionService questionService) {
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
        this.questionService = questionService;
    }

    // for teacher
    public List<StudentTest> findAllByDisciplineId(Long id) {
        return testRepository.findAllByDisciplineId(id);
    }

    // for teacher/student
    public StudentTest findById(Long id) {
        return testRepository.findById(id)  // TODO добавить кастомный эксепшен
                .orElseThrow(() -> new RuntimeException("Test not found!"));
    }

    public List<StudentTest> findAllAvailableTest(Long studentId) { // TODO добавить реализацию
        return testRepository.findAllByIsAvailableEquals(true);
    }

    public List<StudentTest> findAllPassedTest(Long studentId) {  // TODO добавить реализацию ()
        return null;
    }

    public TestResult saveTestResult(List<String> answers, Long testId) {  // TODO добавить сет пользователя
        TestResult testResult = new TestResult();
        testResult.setTest(findById(testId));
        List<String> correctAnswers = questionService.findTestCorrectAnswers(testId);
        testResult.setScore((double) (errorList(correctAnswers, answers).size() / correctAnswers.size()));
        return resultRepository.save(testResult);
    }

    public List<String> errorList(List<String> correct, List<String> check) {
        return correct.stream()
                .filter(element -> !check.contains(element))
                .toList();
    }

    public StudentTest save(StudentTest test) {
        return testRepository.save(test);
    }

    public StudentTest changeTestAvailable(Long id) {
        StudentTest test = findById(id);
        test.setIsAvailable(!test.getIsAvailable());
        return testRepository.save(test);
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }
}
