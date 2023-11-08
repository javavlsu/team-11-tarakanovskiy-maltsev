package com.questions_platform.backend.dto;

import com.questions_platform.backend.domain.TestQuestion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestQuestionDto {
    private Long id;
    private String title;
    private List<TestAnswerDto> answers;

    public TestQuestionDto(TestQuestion question) {
        this.id = question.getId();
        this.title = question.getTitle();
    }
}
