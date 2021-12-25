package com.marketlogicsoftware.survey.service;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.request.QuestionChoiceRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.dto.response.PollResponse;
import com.marketlogicsoftware.survey.dto.response.QuestionChoiceResponse;
import com.marketlogicsoftware.survey.exception.UnfoundEntity;
import com.marketlogicsoftware.survey.mapper.PollMapper;
import com.marketlogicsoftware.survey.model.Poll;
import com.marketlogicsoftware.survey.model.PollQuestion;
import com.marketlogicsoftware.survey.model.QuestionChoice;
import com.marketlogicsoftware.survey.repository.PollQuestionRepository;
import com.marketlogicsoftware.survey.repository.PollRepository;
import com.marketlogicsoftware.survey.repository.QuestionChoiceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PollServiceTest {

    private static final Long POLL_ID = 1L;
    private static final Long QUESTION_ID = 1L;
    private static final Long CHOICE_ID = 1L;

    @Mock
    private Poll poll;
    @Mock
    private PollQuestion pollQuestion;
    @Mock
    private QuestionChoice choice;
    @Mock
    private PollQuestionRequestDto questionRequestDto;
    @Mock
    private QuestionChoiceRequestDto choiceRequestDto;
    @Mock
    private PollQuestionResponse pollQuestionResponse;
    @Mock
    private QuestionChoiceResponse choiceResponse;
    @Mock
    private PollResponse pollResponse;
    @Mock
    private PollRepository pollRepository;
    @Mock
    private PollQuestionRepository pollQuestionRepository;
    @Mock
    private QuestionChoiceRepository questionChoiceRepository;
    @Mock
    private PollMapper pollMapper;
    @InjectMocks
    private PollService pollService;
    private List<PollQuestion> pollQuestions = Arrays.asList(pollQuestion);

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
    public void shouldThrowExceptionWhenPollIsNotFound() {
        //given
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> pollService.createQuestion(POLL_ID, questionRequestDto));

        //then
        assertThat(throwable)
                .isInstanceOf(UnfoundEntity.class)
                .hasMessage(String.format("Poll with given id '%s' is not found", POLL_ID));
    }

    @Test
    public void shouldDeleteQuestion() {
        //given
        PollQuestion mockPollQuestion = new PollQuestion();
        mockPollQuestion.setId(QUESTION_ID);
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.of(poll));
        given(poll.getQuestions()).willReturn(Arrays.asList(mockPollQuestion));
        given(pollMapper.from(poll)).willReturn(pollResponse);

        //when
        PollResponse response = pollService.deleteQuestion(POLL_ID, QUESTION_ID);

        //then
        assertThat(response).isEqualTo(pollResponse);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteQuestionThatDoesNotExist() {
        //given
        PollQuestion mockPollQuestion = new PollQuestion();
        mockPollQuestion.setId(9999);
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.of(poll));
        given(poll.getQuestions()).willReturn(Arrays.asList(mockPollQuestion));

        //when
        Throwable throwable = catchThrowable(() -> pollService.deleteQuestion(POLL_ID, QUESTION_ID));

        //then
        assertThat(throwable)
                .isInstanceOf(UnfoundEntity.class)
                .hasMessage(String.format("Question with id '%s' is not associated with a poll with id '%s'",
                        QUESTION_ID, poll.getId()));
    }

    @Test
    public void shouldCreateChoice() {
        //given
        PollQuestion mockPollQuestion = new PollQuestion();
        mockPollQuestion.setId(QUESTION_ID);
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.of(poll));
        given(poll.getQuestions()).willReturn(Arrays.asList(mockPollQuestion));
        given(pollMapper.from(choiceRequestDto)).willReturn(choice);
        given(questionChoiceRepository.save(choice)).willReturn(choice);
        given(pollMapper.from(choice)).willReturn(choiceResponse);

        //when
        QuestionChoiceResponse response = pollService.createChoice(POLL_ID, QUESTION_ID, choiceRequestDto);

        //then
        assertThat(response).isEqualTo(choiceResponse);
    }

    @Test
    public void shouldDeleteChoice() {
        //given
        QuestionChoice mockChoice = new QuestionChoice();
        mockChoice.setId(CHOICE_ID);
        PollQuestion mockPollQuestion = new PollQuestion();
        mockPollQuestion.setId(QUESTION_ID);
        mockPollQuestion.setChoices(new ArrayList<>(Arrays.asList(mockChoice)));
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.of(poll));
        given(poll.getQuestions()).willReturn(Arrays.asList(mockPollQuestion));
        given(pollMapper.from(mockPollQuestion)).willReturn(pollQuestionResponse);

        //when
        PollQuestionResponse response = pollService.deleteChoice(POLL_ID, POLL_ID, CHOICE_ID);
        //then
        assertThat(response).isEqualTo(pollQuestionResponse);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteChoiceThatDoesNotExist() {
        //given
        QuestionChoice mockChoice = new QuestionChoice();
        mockChoice.setId(9999);
        PollQuestion mockPollQuestion = new PollQuestion();
        mockPollQuestion.setId(QUESTION_ID);
        mockPollQuestion.setChoices(new ArrayList<>(Arrays.asList(mockChoice)));
        given(pollRepository.findById(POLL_ID)).willReturn(Optional.of(poll));
        given(poll.getQuestions()).willReturn(Arrays.asList(mockPollQuestion));

        //when
        Throwable throwable = catchThrowable(() -> pollService.deleteChoice(POLL_ID, QUESTION_ID, CHOICE_ID));

        //then
        assertThat(throwable)
                .isInstanceOf(UnfoundEntity.class)
                .hasMessage(String.format("Choice with id '%s' is not associated with a question with id '%s'",
                        CHOICE_ID, QUESTION_ID));
    }
}