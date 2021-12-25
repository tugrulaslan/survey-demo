package com.marketlogicsoftware.survey.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PollResponseRequestDto {
    @Valid
    @NotNull(message = "The Poll must be provided with some choices to the questions")
    @Size(min = 1, message = "The Poll must be provided at least with one response")
    private List<PollQuestionResponseRequest> responses;

    public PollResponseRequestDto() {
    }

    public PollResponseRequestDto(List<PollQuestionResponseRequest> responses) {
        this.responses = responses;
    }

    public List<PollQuestionResponseRequest> getResponses() {
        return responses;
    }

    public void setResponses(List<PollQuestionResponseRequest> responses) {
        this.responses = responses;
    }
}
