package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.TestAnswer;
import com.questions_platform.backend.domain.TestQuestion;
import com.questions_platform.backend.dto.TestQuestionDto;
import com.questions_platform.backend.repository.TestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestQuestionService {
    private final TestQuestionRepository testQuestionRepository;
    private final TestAnswerService testAnswerService;

    public TestQuestionService(TestQuestionRepository testQuestionRepository,
                               TestAnswerService testAnswerService) {
        this.testQuestionRepository = testQuestionRepository;
        this.testAnswerService = testAnswerService;
    }

    public List<TestQuestionDto> findAllByTestId(Long testId) {
        List<TestQuestionDto> questions = testQuestionRepository.findAllByTestId(testId).stream()
                .map(TestQuestionDto::new).toList();
        questions.stream()
                .peek(q -> q.setAnswers(testAnswerService.findAllByQuestionId(q.getId())))
                .toList();
        return questions;
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

    public TestQuestion save(TestQuestion testQuestion) {
        return testQuestionRepository.saveAndFlush(testQuestion);
    }

    public void delete(Long questionId) {
        testQuestionRepository.deleteById(questionId);
    }
}
