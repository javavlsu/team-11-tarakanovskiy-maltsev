package com.questions_platform.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_question")
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    // links
    @ManyToOne
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private StudentTest test;
    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<TestAnswer> answers = new HashSet<>();
}
