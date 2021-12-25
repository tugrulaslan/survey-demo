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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PollService {

    //TODO: FIX THE MESSAGE
    private static final String QUESTION_EXCEPTION_MESSAGE = "Poll with id '%s' is not associated " +
            "with a question with given id '%s'";
    private static final String CHOICE_EXCEPTION_MESSAGE = "Choice with id '%s' is not associated " +
            "with a question with id '%s'";

    private final PollRepository pollRepository;
    private final PollQuestionRepository pollQuestionRepository;
    private final QuestionChoiceRepository questionChoiceRepository;
    private final PollMapper pollMapper;

    public PollService(PollRepository pollRepository,
                       PollQuestionRepository pollQuestionRepository,
                       QuestionChoiceRepository questionChoiceRepository,
                       PollMapper pollMapper) {
        this.pollRepository = pollRepository;
        this.pollQuestionRepository = pollQuestionRepository;
        this.questionChoiceRepository = questionChoiceRepository;
        this.pollMapper = pollMapper;
    }

    public PollQuestionResponse createQuestion(Long pollId, PollQuestionRequestDto requestDto) {
        Poll poll = retrievePoll(pollId);
        PollQuestion question = pollMapper.from(requestDto);
        question.setPoll(poll);
        PollQuestion persistedQuestion = pollQuestionRepository.save(question);
        return pollMapper.from(persistedQuestion);
    }

    public PollResponse deleteQuestion(Long pollId, Long questionId) {
        Poll poll = retrievePoll(pollId);
        PollQuestion pollQuestion = retrievePollQuestion(questionId, poll);
        poll.removeQuestion(pollQuestion);
        return pollMapper.from(poll);
    }

    public QuestionChoiceResponse createChoice(Long pollId, Long questionId, QuestionChoiceRequestDto requestDto) {
        Poll poll = retrievePoll(pollId);
        PollQuestion pollQuestion = retrievePollQuestion(questionId, poll);
        QuestionChoice choice = pollMapper.from(requestDto);
        choice.setPollQuestion(pollQuestion);
        QuestionChoice persistedChoice = questionChoiceRepository.save(choice);
        return pollMapper.from(persistedChoice);
    }

    public PollQuestionResponse deleteChoice(Long pollId, Long questionId, Long choiceId) {
        Poll poll = retrievePoll(pollId);
        PollQuestion pollQuestion = retrievePollQuestion(questionId, poll);
        QuestionChoice choice = retrieveQuestionChoice(choiceId, pollQuestion);
        pollQuestion.removeChoice(choice);
        return pollMapper.from(pollQuestion);
    }

    private Poll retrievePoll(long pollId) {
        Optional<Poll> optionalPoll = pollRepository.findById(pollId);
        if (optionalPoll.isEmpty()) {
            throw new UnfoundEntity(String.format("Poll with given id '%s' is not found", pollId));
        }
        return optionalPoll.get();
    }

    private PollQuestion retrievePollQuestion(Long questionId, Poll poll) {
        return poll.getQuestions().stream()
                .filter(question -> question.getId() == questionId)
                .findFirst()
                .orElseThrow(() ->
                        new UnfoundEntity(String.format(QUESTION_EXCEPTION_MESSAGE, poll.getId(), questionId)));
    }

    private QuestionChoice retrieveQuestionChoice(Long choiceId, PollQuestion pollQuestion) {
        return pollQuestion.getChoices().stream()
                .filter(choice -> choice.getId() == choiceId)
                .findFirst()
                .orElseThrow(() ->
                        new UnfoundEntity(String.format(CHOICE_EXCEPTION_MESSAGE, choiceId, pollQuestion.getId())));
    }
}
