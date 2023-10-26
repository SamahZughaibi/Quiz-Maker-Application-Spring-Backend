package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.Question;
import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    //creating a user and assigning quizzes with questions to them
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
    void findAll__allQuestionsInSystem(){
        assertEquals(2, questionRepository.findAll().size());
    }
    @Test
    void findAllByHomeQuiz_quizObject_allQuestionsAssignedForTheQuiz(){
        assertEquals(1, questionRepository.findAllByHomeQuiz(quiz).size());
    }
}