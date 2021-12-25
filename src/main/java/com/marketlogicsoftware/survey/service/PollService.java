package com.marketlogicsoftware.survey.service;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.dto.response.PollResponse;
import com.marketlogicsoftware.survey.exception.UnfoundEntity;
import com.marketlogicsoftware.survey.mapper.PollMapper;
import com.marketlogicsoftware.survey.model.Poll;
import com.marketlogicsoftware.survey.model.PollQuestion;
import com.marketlogicsoftware.survey.repository.PollQuestionRepository;
import com.marketlogicsoftware.survey.repository.PollRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PollService {

    private static final String QUESTION_EXCEPTION_MESSAGE = "Poll with id '%s' is not associated " +
            "with a question with given id '%s'";

    private final PollRepository pollRepository;
    private final PollQuestionRepository pollQuestionRepository;
    private final PollMapper pollMapper;

    public PollService(PollRepository pollRepository,
                       PollQuestionRepository pollQuestionRepository,
                       PollMapper pollMapper) {
        this.pollRepository = pollRepository;
        this.pollQuestionRepository = pollQuestionRepository;
        this.pollMapper = pollMapper;
    }

    @Transactional
    public PollQuestionResponse createQuestion(Long pollId, PollQuestionRequestDto requestDto) {
        Poll poll = retrievePoll(pollId);
        PollQuestion question = pollMapper.from(requestDto);
        question.setPoll(poll);
        PollQuestion persistedQuestion = pollQuestionRepository.save(question);
        return pollMapper.from(persistedQuestion);
    }

    @Transactional
    public PollResponse deleteQuestion(Long pollId, Long questionId) {
        Poll poll = retrievePoll(pollId);
        PollQuestion pollQuestion = retrievePollQuestion(questionId, poll);
        poll.removeQuestion(pollQuestion);
        return pollMapper.from(poll);
    }

    private PollQuestion retrievePollQuestion(Long questionId, Poll poll) {
        return poll.getQuestions().stream()
                .filter(question -> question.getId() == questionId)
                .findFirst()
                .orElseThrow(() ->
                        new UnfoundEntity(String.format(QUESTION_EXCEPTION_MESSAGE, poll.getId(), questionId)));
    }

    private Poll retrievePoll(long pollId) {
        Optional<Poll> optionalPoll = pollRepository.findById(pollId);
        if (optionalPoll.isEmpty()) {
            throw new UnfoundEntity(String.format("Poll with given id '%s' is not found", pollId));
        }
        return optionalPoll.get();
    }
}
