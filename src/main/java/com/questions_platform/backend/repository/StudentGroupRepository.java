package com.questions_platform.backend.repository;

import com.questions_platform.backend.domain.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
}
