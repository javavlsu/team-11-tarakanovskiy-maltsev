package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.TestAnswer;
import com.questions_platform.backend.domain.TestQuestion;
import com.questions_platform.backend.dto.TestQuestionDto;
import com.questions_platform.backend.repository.StudentTestRepository;
import com.questions_platform.backend.repository.TestQuestionRepository;
import com.questions_platform.backend.util.QuestionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestQuestionService {
    private final TestQuestionRepository testQuestionRepository;
    private final TestAnswerService testAnswerService;
    private final StudentTestRepository studentTestRepository;

    public TestQuestionService(TestQuestionRepository testQuestionRepository,
                               TestAnswerService testAnswerService, StudentTestRepository studentTestRepository) {
        this.testQuestionRepository = testQuestionRepository;
        this.testAnswerService = testAnswerService;
        this.studentTestRepository = studentTestRepository;
    }

    public List<TestQuestionDto> findAllByTestId(Long testId) {
        List<TestQuestionDto> questions = testQuestionRepository.findAllByTestId(testId).stream()
                .map(TestQuestionDto::new).toList();
        return questions.stream().peek(q -> q.setAnswers(testAnswerService.findAllByQuestionId(q.getId())))
                .toList();
    }

    public List<String> findTestCorrectAnswers(Long testId){
        List<String> answers = new ArrayList<>();
        List<TestQuestionDto> questions = findAllByTestId(testId);
        for (var q : questions){
            answers.add(findQuestionCorrectAnswer(q.getId()));
        }
        return answers;
    }

    public String findQuestionCorrectAnswer(Long questionId){
        TestAnswer answer = testAnswerService.findCorrectByQuestionId(questionId);
        return answer.getText();
    }

    public void save(TestQuestion testQuestion, Long testId) {
        testQuestion.setTest(studentTestRepository.findById(testId).orElseThrow());
        testQuestionRepository.saveAndFlush(testQuestion);
    }

    public void delete(Long questionId) {
        testQuestionRepository.deleteById(questionId);
    }

    public TestQuestion findById(Long questionId) {
        return testQuestionRepository.findById(questionId)
                .orElseThrow( () -> new QuestionNotFoundException(questionId));
    }
}
