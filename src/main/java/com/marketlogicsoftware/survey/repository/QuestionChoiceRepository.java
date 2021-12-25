package com.marketlogicsoftware.survey.repository;

import com.marketlogicsoftware.survey.model.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {
}
