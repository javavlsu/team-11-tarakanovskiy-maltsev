package com.questions_platform.backend.repository;

import com.questions_platform.backend.domain.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {
    List<TestQuestion> findAllByTestId(Long id);
}
