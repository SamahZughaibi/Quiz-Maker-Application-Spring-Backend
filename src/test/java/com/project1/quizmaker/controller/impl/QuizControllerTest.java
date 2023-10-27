package com.project1.quizmaker.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.quizmaker.model.*;
import com.project1.quizmaker.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class QuizControllerTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizResultRepository quizResultRepository;
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    WebApplicationContext webApplicationContext1;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    User user1, quizTaker;
    Quiz quiz;
    QuizResult quizResult;
    Question question;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext1).build();

        user1 = new User("samah@gmail.com", "Samah Alzughaibi");
        userRepository.save(user1);
        quiz = new Quiz("Around the World", user1);
        quizRepository.save(quiz);

        quizTaker = new User("shouq@gmail.com", "Shouq Alharbi");
        userRepository.save(quizTaker);
        quizResult = new QuizResult(quizTaker, quiz, new Date(), 20);
        quizResultRepository.save(quizResult);

        question = new Question("Which country is largest area?", 40, quiz);
        questionRepository.save(question);

//        choice = new Choice("Russia", true, question);
//        choiceRepository.save(choice);

        //for post test
//        userToPost = new User("hussa@gmail.com","Hussa Abdulaziz");
    }
    @AfterEach
    public void tearDown() {
//        userRepository.deleteById("hussa@gmail.com");
//        choiceRepository.deleteById(choice.getChoiceId());
        questionRepository.deleteById(question.getQuestionId());
        quizResultRepository.deleteById(quizResult.getQuizResultId());
        userRepository.deleteById("shouq@gmail.com");
        quizRepository.deleteById(quiz.getQuizId());
        userRepository.deleteById("samah@gmail.com");
    }

    //----------------------------------GET TEST-------------------------------------
    @Test
    void getAllQuizzes_validRequest_allQuizzes() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/quizzes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Around the World"));
    }

    //-------------------------------- DELETE TEST ----------------------------------
    @Test
    void deleteQuiz_validRequest_quizDeleted() throws Exception {
        Quiz quizToDelete = new Quiz("to be deleted", user1);
        quizRepository.save(quizToDelete);

        mockMvc.perform(delete("/api/quizzes/"+quizToDelete.getQuizId()))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(quizRepository.findAll().toString().contains("to be deleted"));
    }


}