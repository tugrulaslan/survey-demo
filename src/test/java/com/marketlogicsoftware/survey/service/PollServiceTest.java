package com.marketlogicsoftware.survey.service;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.exception.UnfoundEntity;
import com.marketlogicsoftware.survey.mapper.PollMapper;
import com.marketlogicsoftware.survey.model.Poll;
import com.marketlogicsoftware.survey.model.PollQuestion;
import com.marketlogicsoftware.survey.repository.PollQuestionRepository;
import com.marketlogicsoftware.survey.repository.PollRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PollServiceTest {

    private static final Long POLL_ID = 1L;

    @Mock
    private Poll poll;
    @Mock
    private PollQuestion pollQuestion;
    @Mock
    private PollQuestionRequestDto questionRequestDto;
    @Mock
    private PollQuestionResponse pollQuestionResponse;
    @Mock
    private PollRepository pollRepository;
    @Mock
    private PollQuestionRepository pollQuestionRepository;
    @Mock
    private PollMapper pollMapper;
    @InjectMocks
    private PollService pollService;

    @Test
    public void shouldCreateQuestion() {
        //given
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.of(poll));
        given(pollMapper.from(questionRequestDto)).willReturn(pollQuestion);
        given(pollQuestionRepository.save(pollQuestion)).willReturn(pollQuestion);
        given(pollMapper.from(pollQuestion)).willReturn(pollQuestionResponse);

        //when
        PollQuestionResponse response = pollService.createQuestion(POLL_ID, questionRequestDto);

        //then
        assertThat(response).isEqualTo(pollQuestionResponse);
    }

    @Test
    public void shouldThrowExceptionWhenPollIsNotFound(){
        //given
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> pollService.createQuestion(POLL_ID, questionRequestDto));

        //then
        assertThat(throwable)
                .isInstanceOf(UnfoundEntity.class)
                .hasMessage(String.format("Poll with given id '%s' is not found", POLL_ID));
    }
}