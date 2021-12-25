package com.marketlogicsoftware.survey.mapper;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.request.QuestionChoiceRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.dto.response.PollResponse;
import com.marketlogicsoftware.survey.dto.response.QuestionChoiceResponse;
import com.marketlogicsoftware.survey.model.Poll;
import com.marketlogicsoftware.survey.model.PollQuestion;
import com.marketlogicsoftware.survey.model.QuestionChoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PollMapper {
    PollQuestion from(PollQuestionRequestDto requestDto);

    PollQuestionResponse from(PollQuestion pollQuestion);

    PollResponse from(Poll poll);

    QuestionChoice from(QuestionChoiceRequestDto requestDto);

    QuestionChoiceResponse from(QuestionChoice questionChoice);

}
