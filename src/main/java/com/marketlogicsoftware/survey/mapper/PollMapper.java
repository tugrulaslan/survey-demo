package com.marketlogicsoftware.survey.mapper;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.dto.response.PollResponse;
import com.marketlogicsoftware.survey.model.Poll;
import com.marketlogicsoftware.survey.model.PollQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PollMapper {
    PollQuestion from(PollQuestionRequestDto requestDto);
    PollQuestionResponse from(PollQuestion pollQuestion);
    PollResponse from(Poll poll);
}
