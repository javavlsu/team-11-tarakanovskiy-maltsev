package com.questions_platform.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_test")
public class StudentTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_end")
    private LocalDate dateOfEnd;
    @Column(name = "is_available") // TODO посмотреть columnDefinition поставить false mb
    private Boolean isAvailable;

    // links TODO добавить создателя теста??, студентов прошедших тест
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @JsonIgnore
    private Discipline discipline;

    @OneToMany(mappedBy = "test", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<TestQuestion> questions = new HashSet<>();

    @OneToMany(mappedBy = "test", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<TestResult> results = new HashSet<>();

}
