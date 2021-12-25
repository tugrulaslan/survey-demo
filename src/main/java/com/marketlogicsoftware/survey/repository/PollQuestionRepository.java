package com.marketlogicsoftware.survey.repository;

import com.marketlogicsoftware.survey.model.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollQuestionRepository extends JpaRepository<PollQuestion, Long> {
}
