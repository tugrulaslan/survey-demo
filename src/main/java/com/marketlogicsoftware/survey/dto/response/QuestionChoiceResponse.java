package com.marketlogicsoftware.survey.dto.response;

public class QuestionChoiceResponse {
    private long id;
    private String choice;

    public QuestionChoiceResponse() {
    }

    public QuestionChoiceResponse(long id, String choice) {
        this.id = id;
        this.choice = choice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
