package com.questions_platform.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @Column(name = "is_available")
    private Boolean isAvailable;

    // links TODO добавить создателя теста??
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @JsonIgnore
    private Discipline discipline;

    @ManyToMany
    @JoinTable(
            name = "test_user",
            joinColumns = { @JoinColumn(name = "test_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }
    )
    @JsonIgnore
    private List<User> passedStudent = new ArrayList<>();

    @OneToMany(mappedBy = "test", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<TestQuestion> questions = new HashSet<>();

    @OneToMany(mappedBy = "test", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<TestResult> results = new HashSet<>();

}
