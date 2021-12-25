package com.marketlogicsoftware.survey.dto.response;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionChoiceResponse that = (QuestionChoiceResponse) o;
        return id == that.id && choice.equals(that.choice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, choice);
    }
}
