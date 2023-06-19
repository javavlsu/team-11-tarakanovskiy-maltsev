package com.questions_platform.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    // links
    @ManyToMany(mappedBy = "disciplines")
    @JsonIgnore
    private Set<StudentGroup> groups = new HashSet<>();
    @OneToMany(mappedBy = "discipline", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<StudentTest> tests = new HashSet<>();
}
