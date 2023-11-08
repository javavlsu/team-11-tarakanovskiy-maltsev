package com.questions_platform.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "group_discipline",
            joinColumns = { @JoinColumn(name = "discipline_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "id") }
    )
    @JsonIgnore
    private List<StudentGroup> groups = new ArrayList<>();
    @OneToMany(mappedBy = "discipline", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<StudentTest> tests = new HashSet<>();
}
