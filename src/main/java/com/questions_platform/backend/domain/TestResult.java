package com.questions_platform.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_result")
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "score")
    private Double score;

    // links
    @ManyToOne
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private StudentTest test;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private User student;
}
