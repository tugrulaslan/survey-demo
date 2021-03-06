package com.marketlogicsoftware.survey.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "poll_questions")
public class PollQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "pollQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<QuestionChoice> choices;

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

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public List<QuestionChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<QuestionChoice> choices) {
        this.choices = choices;
    }

    public void removeChoice(QuestionChoice choice) {
        choices.remove(choice);
        choice.setPollQuestion(null);
    }
}



