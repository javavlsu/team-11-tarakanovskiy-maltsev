package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.Discipline;
import com.questions_platform.backend.domain.StudentGroup;
import com.questions_platform.backend.repository.DisciplineRepository;
import com.questions_platform.backend.repository.StudentGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DisciplineGroupService {
    private final DisciplineRepository disciplineRepository;
    private final StudentGroupRepository groupRepository;

    public DisciplineGroupService(DisciplineRepository disciplineRepository,
                                  StudentGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> findAll(){
        return disciplineRepository.findAll();
    }

    public List<Discipline> findByGroupId(Long id){
        return disciplineRepository.findAllByGroupId(id);
    }

    public Discipline saveDiscipline(Discipline discipline){
        return disciplineRepository.save(discipline);
    }

    public Discipline findDisciplineById(Long id){
        return disciplineRepository.findById(id)  // TODO добавить кастомный эксепшен
                .orElseThrow( () -> new RuntimeException("Discipline not found!"));
    }

    public StudentGroup saveGroup(StudentGroup studentGroup){
        return groupRepository.save(studentGroup);
    }

    public StudentGroup findGroupById(Long id){
        return groupRepository.findById(id)  // TODO добавить кастомный эксепшен
                .orElseThrow( () -> new RuntimeException("Group not found!"));
    }

    public Discipline changeGroupDiscipline(Long disciplineId, Long groupId){
        Discipline discipline = findDisciplineById(disciplineId);
        Set<StudentGroup> groups = discipline.getGroups();
        StudentGroup group = findGroupById(groupId);

        if (groups.contains(group)){
            groups.remove(group);
        } else {
            groups.add(group);
        }

        discipline.setGroups(groups);
        return saveDiscipline(discipline);
    }
}
