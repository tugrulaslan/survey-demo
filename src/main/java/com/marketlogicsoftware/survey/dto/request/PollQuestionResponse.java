package com.marketlogicsoftware.survey.dto.request;

import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PollQuestionResponse {
    @NotNull(message = "The Question Id cannot be left null")
    @Range(min = 1L, message = "Please select only positive numbers for the question id")
    private Long questionId;

    @Valid
    @NotNull(message = "The Question's choices cannot be left null")
    @Range(min = 1L, message = "Please select only positive numbers for the choice id of a given question")
    @Size(min = 1, message = "The Question has be fulfilled at least with one choice")
    private List<Long> choices;

    public PollQuestionResponse() {
    }

    public PollQuestionResponse(Long questionId, List<Long> choices) {
        this.questionId = questionId;
        this.choices = choices;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Long> getChoices() {
        return choices;
    }

    public void setChoices(List<Long> choices) {
        this.choices = choices;
    }
}
