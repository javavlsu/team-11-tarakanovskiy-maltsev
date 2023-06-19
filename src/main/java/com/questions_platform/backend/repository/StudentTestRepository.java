package com.questions_platform.backend.repository;

import com.questions_platform.backend.domain.StudentTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentTestRepository extends JpaRepository<StudentTest, Long> {
    List<StudentTest> findAllByDisciplineId(Long id);
    List<StudentTest> findAllByIsAvailableEquals(Boolean state);
}
