package com.marketlogicsoftware.survey.dto.response;

import java.util.List;

public class PollQuestionResponse {
    private long id;
    private String question;
    List<QuestionChoiceResponse> choices;

    public PollQuestionResponse() {
    }

    public PollQuestionResponse(long id, String question, List<QuestionChoiceResponse> choices) {
        this.id = id;
        this.question = question;
        this.choices = choices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuestionChoiceResponse> getChoices() {
        return choices;
    }

    public void setChoices(List<QuestionChoiceResponse> choices) {
        this.choices = choices;
    }
}
