package com.marketlogicsoftware.survey.repository;

import com.marketlogicsoftware.survey.model.ChoiceCount;
import com.marketlogicsoftware.survey.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    @Query(value = "SELECT choice_id AS choiceId, COUNT(c.*) AS totalCount "
            + "FROM response_choices AS c where c.choice_id =:choiceId GROUP BY choice_id ORDER BY choice_id DESC",
            nativeQuery = true)
    Optional<ChoiceCount> calculateChoiceTotalCount(@Param("choiceId") long choiceId);
}
