package com.marketlogicsoftware.survey.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuestionChoiceRequestDto {
    @NotNull(message = "The Question Choice cannot be left null")
    @Size(min = 2, message = "The Question Choice cannot be left less than 2 characters")
    private String choice;

    public QuestionChoiceRequestDto() {
    }

    public QuestionChoiceRequestDto(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
