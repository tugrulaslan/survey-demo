package com.marketlogicsoftware.survey.mapper;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.request.QuestionChoiceRequestDto;
import com.marketlogicsoftware.survey.dto.response.*;
import com.marketlogicsoftware.survey.model.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PollMapper {
    PollQuestion from(PollQuestionRequestDto requestDto);

    PollQuestionResponse from(PollQuestion pollQuestion);

    PollResponse from(Poll poll);

    QuestionChoice from(QuestionChoiceRequestDto requestDto);

    QuestionChoiceResponse from(QuestionChoice questionChoice);

    List<UserPollResponse> from(List<Response> responseList);

    PollStatisticsResponse from(ChoiceCount count);

}
