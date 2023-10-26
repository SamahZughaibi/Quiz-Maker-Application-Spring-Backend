package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuizRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    //creating 2 users and assigning quizzes with questions to them
    User user1 = new User("Spongebob@gmail.com","SpongeBob SquarePants");
    Quiz quiz = new Quiz("Bikini Bottom", user1);
    Question question = new Question("Who lives in a pineapple under the sea?", 50, quiz);
    User user2 = new User("Patrick@gmail.com", "Patrick Star");
    Quiz quiz2 = new Quiz("Bikini Bottom", user2);
    Question question2 = new Question("Who you callin' pinhead?", 60, quiz2);

    @BeforeEach
    void setUp(){
        userRepository.save(user1);
        userRepository.save(user2);
        quizRepository.save(quiz);
        questionRepository.save(question);
        quizRepository.save(quiz2);
        questionRepository.save(question2);
    }
    @AfterEach
    void tearDown(){
        questionRepository.deleteById(question2.getQuestionId());
        quizRepository.deleteById(quiz2.getQuizId());
        questionRepository.deleteById(question.getQuestionId());
        quizRepository.deleteById(quiz.getQuizId());
        userRepository.deleteById(user1.getEmail());
        userRepository.deleteById(user2.getEmail());
    }

    @Test
    void findAll__allQuizzesInSystem(){
        assertEquals(2, quizRepository.findAll().size());
    }

    @Test
    void findAllByOwner_userObject_allQuizzesByTheUser(){
        assertEquals(1, quizRepository.findAllByOwner(user1).size());
    }

    @Test
    void calculateScoreForQuiz_validQuizId_correctTotalScore(){
        assertEquals(50, quizRepository.calculateScoreForQuiz(quiz.getQuizId()));
    }
    @Test
    void calculateScoreForQuiz_invalidQuizId_correctTotalScore(){
        assertEquals(null, quizRepository.calculateScoreForQuiz(0));
    }
}