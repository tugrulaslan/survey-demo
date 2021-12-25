package com.marketlogicsoftware.survey.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketlogicsoftware.survey.dto.request.PollQuestionRequestDto;
import com.marketlogicsoftware.survey.dto.request.QuestionChoiceRequestDto;
import com.marketlogicsoftware.survey.dto.response.PollQuestionResponse;
import com.marketlogicsoftware.survey.dto.response.PollResponse;
import com.marketlogicsoftware.survey.dto.response.QuestionChoiceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PollsControllerTest {

    private static final long POLL_ID = 1;
    private static final long QUESTION_ID = 99999;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("classpath:db/test-insert-data.sql")
    @Sql(scripts = "classpath:db/test-remove-data.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateQuestion() throws Exception {
        //given
        PollQuestionRequestDto request = new PollQuestionRequestDto("is it really worth to code?");

        //when
        ResultActions resultActions = mockMvc.perform(post(String.format("/api/polls/%s/questions", POLL_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions.andExpect(status().isCreated());
        PollQuestionResponse response =
                objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
                        PollQuestionResponse.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getQuestion()).isEqualTo(request.getQuestion());
    }

    @Test
    public void shouldThrowExceptionCreateQuestionProvidedWithInvalidRequest() throws Exception {
        //given
        PollQuestionRequestDto request = new PollQuestionRequestDto(null);

        //when
        ResultActions resultActions = mockMvc.perform(post(String.format("/api/polls/%s/questions", POLL_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()
                        .getMessage()).contains("The Poll Question cannot be left null"));
    }

    @Test
    @Sql("classpath:db/test-insert-data.sql")
    @Sql(scripts = "classpath:db/test-remove-data.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldDeleteQuestion() throws Exception {
        //given-when
        ResultActions resultActions = mockMvc.perform(delete(String.format("/api/polls/%s/questions/%s", POLL_ID, QUESTION_ID))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
        PollResponse response =
                objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
                        PollResponse.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getQuestions()).isEmpty();
    }

    @Test
    @Sql("classpath:db/test-insert-data.sql")
    @Sql(scripts = "classpath:db/test-remove-data.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateChoice() throws Exception {
        //given
        QuestionChoiceRequestDto request = new QuestionChoiceRequestDto("java");

        //when
        ResultActions resultActions = mockMvc.perform(post(String.format("/api/polls/%s/questions/%s/choices",
                POLL_ID, QUESTION_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions.andExpect(status().isCreated());
        QuestionChoiceResponse response =
                objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
                        QuestionChoiceResponse.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getChoice()).isEqualTo(request.getChoice());
    }

    @Test
    public void shouldThrowExceptionCreateQuestionProvidedWithInvalidRequest2() throws Exception {
        //given
        QuestionChoiceRequestDto request = new QuestionChoiceRequestDto(null);

        //when
        ResultActions resultActions = mockMvc.perform(post(String.format("/api/polls/%s/questions/%s/choices",
                POLL_ID, QUESTION_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()
                        .getMessage()).contains("The Question Choice cannot be left null"));
    }
}