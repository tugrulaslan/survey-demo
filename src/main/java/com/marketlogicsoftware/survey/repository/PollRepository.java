package com.marketlogicsoftware.survey.repository;

import com.marketlogicsoftware.survey.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
