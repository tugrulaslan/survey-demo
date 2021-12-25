package com.marketlogicsoftware.survey.dto.response;

import java.util.List;

public class UserPollResponse {
    private long id;
    private PollQuestionResponse pollQuestion;
    private List<QuestionChoiceResponse> choices;

    public UserPollResponse() {
    }

    public UserPollResponse(long id, PollQuestionResponse pollQuestion, List<QuestionChoiceResponse> choices) {
        this.id = id;
        this.pollQuestion = pollQuestion;
        this.choices = choices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PollQuestionResponse getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(PollQuestionResponse pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public List<QuestionChoiceResponse> getChoices() {
        return choices;
    }

    public void setChoices(List<QuestionChoiceResponse> choices) {
        this.choices = choices;
    }
}
