package com.questions_platform.backend.repository;

import com.questions_platform.backend.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    // TODO добавить поиск по студенту и тесту; добавить поиск всех результатов по тесту
}
