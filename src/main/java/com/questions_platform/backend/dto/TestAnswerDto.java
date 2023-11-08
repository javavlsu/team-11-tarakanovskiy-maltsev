package com.questions_platform.backend.dto;

import com.questions_platform.backend.domain.TestAnswer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestAnswerDto {
    private Long id;
    private String text;
    private Boolean isCorrect;

    public TestAnswerDto(TestAnswer answer){
        this.id = answer.getId();
        this.text = answer.getText();
        this.isCorrect = answer.getIsCorrect();
    }
}
