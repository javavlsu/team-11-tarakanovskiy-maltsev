package com.questions_platform.backend;

import com.questions_platform.backend.domain.Discipline;
import com.questions_platform.backend.service.DisciplineGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DisciplineTest {
    @Autowired
    private DisciplineGroupService disciplineService;

    @Test
    void testFindDisciplineByGroupName(){
        List<Discipline> disciplines = disciplineService.findByGroupId(2L);
        for (var d : disciplines){
            System.out.println(d.getName());
        }
    }
}
