package com.marketlogicsoftware.survey.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_question_id")
    private PollQuestion pollQuestion;

    @ManyToMany
    @JoinTable(name = "response_choices",
            joinColumns = @JoinColumn(name = "response_id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id"))
    public List<QuestionChoice> choices;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PollQuestion getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(PollQuestion pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public List<QuestionChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<QuestionChoice> choices) {
        this.choices = choices;
    }
}



