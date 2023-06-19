package com.questions_platform.backend.repository;

import com.questions_platform.backend.domain.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    @Query("select d from Discipline d inner join d.groups g where g.id in :id")
    List<Discipline> findAllByGroupId(Long id);
}
