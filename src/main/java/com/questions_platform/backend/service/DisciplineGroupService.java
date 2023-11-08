package com.questions_platform.backend.service;

import com.questions_platform.backend.domain.Discipline;
import com.questions_platform.backend.domain.StudentGroup;
import com.questions_platform.backend.repository.DisciplineRepository;
import com.questions_platform.backend.repository.StudentGroupRepository;
import com.questions_platform.backend.util.DisciplineNotFoundException;
import com.questions_platform.backend.util.GroupNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineGroupService {
    private final DisciplineRepository disciplineRepository;
    private final StudentGroupRepository groupRepository;

    public DisciplineGroupService(DisciplineRepository disciplineRepository,
                                  StudentGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> findAllDiscipline(){
        return disciplineRepository.findAll();
    }

    public List<StudentGroup> findAllGroup(){
        return groupRepository.findAll();
    }

    public List<Discipline> findByGroupId(Long id){
        return disciplineRepository.findAllByGroupId(id);
    }

    public Discipline saveDiscipline(Discipline discipline){
        return disciplineRepository.saveAndFlush(discipline);
    }

    public Discipline findDisciplineById(Long id){
        return disciplineRepository.findById(id)
                .orElseThrow( () -> new DisciplineNotFoundException(id));
    }

    public void saveGroup(StudentGroup studentGroup){
        groupRepository.save(studentGroup);
    }

    public StudentGroup findGroupById(Long id){
        return groupRepository.findById(id)
                .orElseThrow( () -> new GroupNotFoundException(id));
    }

    public Discipline changeGroupDiscipline(Long disciplineId, Long groupId){
        Discipline discipline = findDisciplineById(disciplineId);
        StudentGroup group = findGroupById(groupId);

        for (var g : discipline.getGroups()){
            if (g.equals(group)){
                discipline.getGroups().remove(group);
                return saveDiscipline(discipline);
            }
        }
        discipline.getGroups().add(group);
        return saveDiscipline(discipline);
    }
}
