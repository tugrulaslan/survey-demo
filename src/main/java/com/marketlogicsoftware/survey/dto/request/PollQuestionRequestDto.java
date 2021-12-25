package com.marketlogicsoftware.survey.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PollQuestionRequestDto {
    @NotNull(message = "The Poll Question cannot be left null")
    @Size(min = 10, message = "The Poll Question cannot be left less than 10 characters")
    private String question;

    public PollQuestionRequestDto() {
    }

    public PollQuestionRequestDto(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
