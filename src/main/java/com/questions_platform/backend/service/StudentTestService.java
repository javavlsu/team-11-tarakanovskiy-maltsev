package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.StudentTest;
import com.questions_platform.backend.domain.TestResult;
import com.questions_platform.backend.domain.User;
import com.questions_platform.backend.repository.DisciplineRepository;
import com.questions_platform.backend.repository.StudentTestRepository;
import com.questions_platform.backend.repository.TestResultRepository;
import com.questions_platform.backend.repository.UserRepository;
import com.questions_platform.backend.util.ResultNotFoundException;
import com.questions_platform.backend.util.TestNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentTestService {
    private final StudentTestRepository testRepository;
    private final TestResultRepository resultRepository;
    private final TestQuestionService questionService;
    private final DisciplineRepository disciplineRepository;
    private final UserRepository userRepository;

    public StudentTestService(StudentTestRepository testRepository,
                              TestResultRepository resultRepository,
                              TestQuestionService questionService,
                              DisciplineRepository disciplineRepository,
                              UserRepository userRepository) {
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
        this.questionService = questionService;
        this.disciplineRepository = disciplineRepository;
        this.userRepository = userRepository;
    }

    public List<StudentTest> findAllByDisciplineId(Long id) {
        return testRepository.findAllByDisciplineId(id).stream()
                .filter(t -> t.getIsAvailable().equals(true)
                        && t.getDateOfEnd().isAfter(LocalDate.now())
                )
                .toList();
    }

    public StudentTest findById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
    }

    public List<StudentTest> findAllAvailableTest(Long studentId) {
        User user = userRepository.findById(studentId).orElseThrow();
        return testRepository.findAll().stream()
                .filter(studentTest -> !studentTest.getPassedStudent().contains(user)
                && studentTest.getIsAvailable().equals(true)
                && studentTest.getDateOfEnd().isAfter(LocalDate.now()))
                .toList();
    }

    public List<StudentTest> findAllPassedTest(Long studentId) {
        User user = userRepository.findById(studentId).orElseThrow();
        return testRepository.findAll().stream()
                .filter(studentTest -> studentTest.getPassedStudent().contains(user))
                .toList();
    }

    public void saveTestResult(HashMap<String, String> answers, Long testId, Long userId) {
        TestResult testResult = new TestResult();
        StudentTest test = findById(testId);
        User user = userRepository.findById(userId).orElseThrow();
        test.getPassedStudent().add(user);
        testResult.setTest(test);
        testResult.setStudent(user);
        List<String> correctAnswers = questionService.findTestCorrectAnswers(testId);
        List<String> userAns = new ArrayList<>(answers.values());
        userAns.remove(0); // delete _csrf token
        testResult.setScore((double) (errorList(correctAnswers, userAns).size() / correctAnswers.size()));
        save(test);
        resultRepository.save(testResult);
    }

    public TestResult findResult(Long testId, Long userId){
        return resultRepository.findByTestIdAndStudentId(testId, userId)
                .orElseThrow( () -> new ResultNotFoundException(testId));
    }

    public List<String> errorList(List<String> correct, List<String> check) {
        return correct.stream()
                .filter(check::contains)
                .toList();
    }

    public void save(StudentTest studentTest){
        if (studentTest.getIsAvailable() == null){
            studentTest.setIsAvailable(false);
        }
        testRepository.save(studentTest);
    }

    public void saveWithDiscipline(StudentTest test) {
        testRepository.save(test);
    }

    public void changeTestAvailable(Long id) {
        StudentTest test = findById(id);
        test.setIsAvailable(!test.getIsAvailable());
        testRepository.save(test);
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    public List<TestResult> findAllResult(Long testId) {
        return resultRepository.findAllByTestId(testId);
    }

    public List<StudentTest> findAll() {
        return testRepository.findAll();
    }
}
