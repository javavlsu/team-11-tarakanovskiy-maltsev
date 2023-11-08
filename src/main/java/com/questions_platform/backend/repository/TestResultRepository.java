package com.questions_platform.backend.repository;

import com.questions_platform.backend.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    Optional<TestResult> findByTestIdAndStudentId(Long testId, Long userId);
    List<TestResult> findAllByTestId(Long testId);
}
