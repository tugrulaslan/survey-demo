package com.marketlogicsoftware.survey.repository;

import com.marketlogicsoftware.survey.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
