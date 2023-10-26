package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChoiceRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ChoiceRepository choiceRepository;

    //creating a user and assigning quizzes with questions with choices to them
    User user1 = new User("Spongebob@gmail.com","SpongeBob SquarePants");
    Quiz quiz = new Quiz("Bikini Bottom", user1);
    Question question = new Question("Who lives in a pineapple under the sea?", 50, quiz);
    Choice choice1 = new Choice("Sponge!", false, question);
    Choice choice2 = new Choice("Bob!", false, question);
    Choice choice3 = new Choice("Square!", false, question);
    Choice choice4 = new Choice("Pants!", false, question);
    Choice choice5 = new Choice("All of the above", true, question);

    @BeforeEach
    void setUp(){
        userRepository.save(user1);
        quizRepository.save(quiz);
        questionRepository.save(question);
        choiceRepository.save(choice1);
        choiceRepository.save(choice2);
        choiceRepository.save(choice3);
        choiceRepository.save(choice4);
        choiceRepository.save(choice5);
    }
    @AfterEach
    void tearDown(){
        choiceRepository.deleteById(choice1.getChoiceId());
        choiceRepository.deleteById(choice2.getChoiceId());
        choiceRepository.deleteById(choice3.getChoiceId());
        choiceRepository.deleteById(choice4.getChoiceId());
        choiceRepository.deleteById(choice5.getChoiceId());

        questionRepository.deleteById(question.getQuestionId());
        quizRepository.deleteById(quiz.getQuizId());
        userRepository.deleteById(user1.getEmail());
    }
    @Test
    void findAllByHomeQuestion_questionObject_allChoicesThatBelongToTheQuestion(){
        assertEquals(5, choiceRepository.findAllByHomeQuestion(question).size());
    }
}