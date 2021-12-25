package com.marketlogicsoftware.survey.service;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.exception.UnfoundEntity;
import com.marketlogicsoftware.survey.mapper.PollMapper;
import com.marketlogicsoftware.survey.model.Poll;
import com.marketlogicsoftware.survey.model.PollQuestion;
import com.marketlogicsoftware.survey.repository.PollQuestionRepository;
import com.marketlogicsoftware.survey.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

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

    public PollQuestionResponse createQuestion(Long pollId, PollQuestionRequestDto requestDto) {
        Poll poll = retrievePoll(pollId);
        PollQuestion question = pollMapper.from(requestDto);
        question.setPoll(poll);
        PollQuestion persistedQuestion = pollQuestionRepository.save(question);
        return pollMapper.from(persistedQuestion);
    }

    private Poll retrievePoll(long pollId) {
        Optional<Poll> optionalPoll = pollRepository.findById(pollId);
        if (optionalPoll.isEmpty()) {
            throw new UnfoundEntity(String.format("Poll with given id '%s' is not found", pollId));
        }
        return optionalPoll.get();
    }
}
