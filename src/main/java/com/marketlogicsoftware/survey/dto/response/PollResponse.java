package com.marketlogicsoftware.survey.dto.response;

import java.util.List;

public class PollResponse {
    private long id;
    private List<PollQuestionResponse> questions;

    public PollResponse() {
    }

    public PollResponse(long id, List<PollQuestionResponse> questions) {
        this.id = id;
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PollQuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PollQuestionResponse> questions) {
        this.questions = questions;
    }
}
