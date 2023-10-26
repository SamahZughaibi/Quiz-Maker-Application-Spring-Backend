package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.model.QuizResult;
import com.project1.quizmaker.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuizResultRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizResultRepository quizResultRepository;

    User user1 = new User("Spongebob@gmail.com","SpongeBob SquarePants");
    Quiz quiz = new Quiz("Bikini Bottom", user1);
    Date date = new Date();
    QuizResult result = new QuizResult( user1, quiz, date, 25);

    @BeforeEach
    void setUp(){
        userRepository.save(user1);
        quizRepository.save(quiz);
        quizResultRepository.save(result);
    }
    @AfterEach
    void tearDown(){
        quizResultRepository.deleteById(result.getQuizResultId());
        quizRepository.deleteById(quiz.getQuizId());
        userRepository.deleteById(user1.getEmail());
    }
    @Test
    void findAll__allResults(){
        assertEquals(1, quizResultRepository.findAll().size());
    }
}