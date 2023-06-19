package com.questions_platform.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_group")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    // links
    @ManyToMany
    @JoinTable(
            name = "group_discipline",
            joinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "discipline_id", referencedColumnName = "id") }
    )
    private Set<Discipline> disciplines = new HashSet<>();
}
