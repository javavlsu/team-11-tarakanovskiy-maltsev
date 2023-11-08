package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.TestAnswer;
import com.questions_platform.backend.domain.TestQuestion;
import com.questions_platform.backend.dto.TestAnswerDto;
import com.questions_platform.backend.repository.TestAnswerRepository;
import com.questions_platform.backend.repository.TestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestAnswerService {
    private final TestAnswerRepository testAnswerRepository;
    private final TestQuestionRepository testQuestionRepository;

    public TestAnswerService(TestAnswerRepository testAnswerRepository,
                             TestQuestionRepository testQuestionRepository) {
        this.testAnswerRepository = testAnswerRepository;
        this.testQuestionRepository = testQuestionRepository;
    }

    public List<TestAnswerDto> findAllByQuestionId(Long id){
        return testAnswerRepository.findAllByQuestionId(id).stream()
                .map(TestAnswerDto::new).toList();
    }

    public List<TestAnswer> findAllById(Long id){
        return testAnswerRepository.findAllByQuestionId(id);
    }

    public void save(TestAnswer testAnswer, Long questionId){
        TestQuestion question = testQuestionRepository.findById(questionId).orElseThrow();
        testAnswer.setQuestion(question);
        if (testAnswer.getIsCorrect() == null){
            testAnswer.setIsCorrect(false);
        }
        testAnswerRepository.save(testAnswer);
    }

    public TestAnswer findCorrectByQuestionId(Long questionId) {
        return testAnswerRepository.findByQuestionIdAndIsCorrectEquals(questionId, true);
    }
}
