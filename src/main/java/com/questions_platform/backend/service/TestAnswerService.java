package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.TestAnswer;
import com.questions_platform.backend.dto.TestAnswerDto;
import com.questions_platform.backend.repository.TestAnswerRepository;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.List;

@Service
public class TestAnswerService {
    private final TestAnswerRepository testAnswerRepository;

    public TestAnswerService(TestAnswerRepository testAnswerRepository) {
        this.testAnswerRepository = testAnswerRepository;
    }

    public List<TestAnswerDto> findAllByQuestionId(Long id){
        return testAnswerRepository.findAllByQuestionId(id).stream()
                .map(TestAnswerDto::new).toList();
    }

    public List<TestAnswer> saveAll(List<TestAnswer> testAnswer){
        return testAnswerRepository.saveAll(testAnswer);
    }

    public TestAnswer findCorrectByQuestionId(Long questionId) {
        return testAnswerRepository.findByQuestionIdAndIsCorrectEquals(questionId, true);
    }
}
