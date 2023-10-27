package com.project1.quizmaker.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.quizmaker.controller.dto.UserFullNameDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizResultRepository quizResultRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ChoiceRepository choiceRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    User user1, quizTaker, userToPost;
    Quiz quiz;
    QuizResult quizResult;
    Question question;
    Choice choice;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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

        choice = new Choice("Russia", true, question);
        choiceRepository.save(choice);

        //for post test
        userToPost = new User("hussa@gmail.com","Hussa Abdulaziz");
    }
    @AfterEach
    public void tearDown() {
        userRepository.deleteById("hussa@gmail.com");
        choiceRepository.deleteById(choice.getChoiceId());
        questionRepository.deleteById(question.getQuestionId());
        quizResultRepository.deleteById(quizResult.getQuizResultId());
        userRepository.deleteById("shouq@gmail.com");
        quizRepository.deleteById(quiz.getQuizId());
        userRepository.deleteById("samah@gmail.com");
    }
//----------------------------------GET TEST-------------------------------------
    @Test
    void getAllUsers_validRequest_allUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Samah"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alzughaibi"));
    }
    @Test
    void getUserById_validEmail_correctUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/user/samah@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Samah"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alzughaibi"));
    }
    @Test
    void getUserById_invalidEmail_userNotFound() throws Exception {
        mockMvc.perform(get("/api/user/nana").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void getAllUserQuizzes_validEmail_allUserQuizzes() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/user/samah@gmail.com/quizzes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Around the World"));
    }
    @Test
    void getAllUserQuizzes_invalidEmail_userNotFound() throws Exception {
        mockMvc.perform(get("/api/user/lala/quizzes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void getAllResultsForUser_validEmail_allUserResults() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/user/shouq@gmail.com/results"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Around the World"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Shouq"));
    }
    @Test
    void getAllResultsForUser_invalidEmail_userNotFound() throws Exception {
        mockMvc.perform(get("/api/user/lala/results").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void getAllResultsForOwnedQuiz_validQuizId_allQuizResults() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/user/samah@gmail.com/resultsOfQuiz?quizId="+quiz.getQuizId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Around the World"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Shouq"));
    }
    @Test
    void getAllResultsForOwnedQuiz_invalidQuizId_quizNotFound() throws Exception {
        mockMvc.perform(get("/api/user/samah@gmail.com/resultsOfQuiz?quizId=0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void getAllResultsForOwnedQuiz_userNotOwner_forbiddenFromAccess() throws Exception {
        mockMvc.perform(get("/api/user/shouq@gmail.com/resultsOfQuiz?quizId="+quiz.getQuizId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
    }
    @Test
    void getAllUserQuestions_validEmail_allUserQuestions() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/user/samah@gmail.com/questions"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Around the World"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Which country is largest area?"));
    }
    @Test
    void getAllUserQuestions_invalidEmail_userNotFound() throws Exception {
        mockMvc.perform(get("/api/user/oohoo/questions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void getAllUserChoices_validEmail_allUserQuizzes() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/user/samah@gmail.com/choices"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Russia"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Which country is largest area?"));
    }
    @Test
    void getAllUserChoices_invalidEmail_userNotFound() throws Exception {
        mockMvc.perform(get("/api/user/hahaha/choices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    //-------------------------- POST TEST -------------------------
    @Test
    void saveUser_validBody_userSaved() throws Exception {
        String body = objectMapper.writeValueAsString(userToPost);
        mockMvc.perform(post("/api/users").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(userRepository.findAll().toString().contains("Hussa"));
    }
    @Test
    void saveQuiz_validBody_quizSaved() throws Exception {
        Quiz newQuiz = new Quiz("test quiz", user1);
        quizRepository.save(newQuiz);
        String body = objectMapper.writeValueAsString(quiz);
        mockMvc.perform(post("/api/users/quizzes").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(quizRepository.findAll().toString().contains("test quiz"));
        quizRepository.deleteById(newQuiz.getQuizId());
    }

    //-------------------------- PATCH TEST ------------------------
    @Test
    void updateUserFullName_validBody_userUpdated() throws Exception {
        User user = new User("testPerson@test.com", "Test Person");
        userRepository.save(user);

        UserFullNameDTO userFullNameDTO = new UserFullNameDTO("Cool Nameson");

        String body = objectMapper.writeValueAsString(userFullNameDTO);

        mockMvc.perform(patch("/api/user/fullName/testPerson@test.com").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(userRepository.findAll().toString().contains("Cool Nameson"));
        userRepository.deleteById(user.getEmail());
    }

}