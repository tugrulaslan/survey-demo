package com.marketlogicsoftware.survey.model;

import javax.persistence.*;

@Entity
@Table(name = "question_choices")
public class QuestionChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String choice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="poll_question_id")
    private PollQuestion pollQuestion;

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

    public PollQuestion getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(PollQuestion pollQuestion) {
        this.pollQuestion = pollQuestion;
    }
}
