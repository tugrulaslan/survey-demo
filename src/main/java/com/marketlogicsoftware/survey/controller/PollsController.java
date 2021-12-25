package com.marketlogicsoftware.survey.controller;

import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.request.PollResponseRequestDto;
import com.marketlogicsoftware.survey.dto.request.QuestionChoiceRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.dto.response.PollResponse;
import com.marketlogicsoftware.survey.dto.response.QuestionChoiceResponse;
import com.marketlogicsoftware.survey.dto.response.UserPollResponseList;
import com.marketlogicsoftware.survey.service.PollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PollsController {

    private final PollService pollService;

    public PollsController(PollService pollService) {
        this.pollService = pollService;
    }

    // TODO: a)   Add/edit/delete questions and answers
    @PostMapping("/polls/{pollId}/questions")
    public ResponseEntity<PollQuestionResponse> createQuestion(@Valid @PathVariable Long pollId,
                                                               @Valid @RequestBody PollQuestionRequestDto requestDto) {
        PollQuestionResponse question = pollService.createQuestion(pollId, requestDto);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @PatchMapping("/polls/{pollId}/questions/{questionId}")
    public ResponseEntity<String> editQuestion(@Valid @PathVariable Long pollId,
                                               @Valid @PathVariable Long questionId,
                                               @Valid @RequestBody PollQuestionRequestDto requestDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/polls/{pollId}/questions/{questionId}")
    public ResponseEntity<PollResponse> deleteQuestion(@Valid @PathVariable Long pollId,
                                                       @Valid @PathVariable Long questionId) {
        PollResponse pollResponse = pollService.deleteQuestion(pollId, questionId);
        return new ResponseEntity<>(pollResponse, HttpStatus.OK);
    }

    //  TODO: a)  Add/edit/delete questions and answers
    @PostMapping("/polls/{pollId}/questions/{questionId}/choices")
    public ResponseEntity<QuestionChoiceResponse> createChoice(@Valid @PathVariable Long pollId,
                                                               @Valid @PathVariable Long questionId,
                                                               @Valid @RequestBody QuestionChoiceRequestDto requestDto) {
        QuestionChoiceResponse response = pollService.createChoice(pollId, questionId, requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/polls/{pollId}/questions/{questionId}/choices/{choiceId}")
    public ResponseEntity<String> editChoice(@Valid @PathVariable Long pollId,
                                             @Valid @PathVariable Long questionId,
                                             @Valid @PathVariable Long choiceId,
                                             @Valid @RequestBody QuestionChoiceRequestDto requestDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/polls/{pollId}/questions/{questionId}/choices/{choiceId}")
    public ResponseEntity<PollQuestionResponse> deleteChoice(@Valid @PathVariable Long pollId,
                                                             @Valid @PathVariable Long questionId,
                                                             @Valid @PathVariable Long choiceId) {
        PollQuestionResponse response = pollService.deleteChoice(pollId, questionId, choiceId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO: b) read a list of all questions
    @GetMapping("/polls/{pollId}/questions")
    public ResponseEntity<String> listPollQuestions(@Valid @PathVariable Long pollId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    //TODO: c) read a question with all answers
    @GetMapping("/polls/{pollId}/questions/{questionId}")
    public ResponseEntity<String> listPollQuestions(@Valid @PathVariable Long pollId,
                                                    @Valid @PathVariable Long questionId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    //TODO: d) respond to a survey
    @PostMapping("/polls/{pollId}/responses")
    public ResponseEntity<UserPollResponseList> respondToPoll(@Valid @PathVariable Long pollId,
                                                @Valid @RequestBody PollResponseRequestDto requestDto) {
        UserPollResponseList response = pollService.respondToPoll(pollId, requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //TODO: e) Get the relative distribution of a selected answer by question
    @GetMapping("/polls/{pollId}/responses/{responseId}/questions/{questionId}")
    public ResponseEntity<String> calculateRelativeDistribution(@Valid @PathVariable Long pollId,
                                                                @Valid @PathVariable Long responseId,
                                                                @Valid @PathVariable Long questionId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
