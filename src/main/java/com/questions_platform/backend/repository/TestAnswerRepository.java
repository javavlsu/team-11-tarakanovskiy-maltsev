package com.questions_platform.backend.repository;

import com.questions_platform.backend.domain.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {
    List<TestAnswer> findAllByQuestionId(Long id);
    TestAnswer findByQuestionIdAndIsCorrectEquals(Long id, Boolean state);
}
