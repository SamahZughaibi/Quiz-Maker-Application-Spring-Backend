package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        User user1 = new User("Spongebob@gmail.com","SpongeBob SquarePants");
        User user2 = new User("Patrick@gmail.com", "Patrick Star");
        userRepository.save(user1);
        userRepository.save(user2);
    }
    @AfterEach
    void tearDown(){
        userRepository.deleteById("spongebob@gmail.com");
        userRepository.deleteById("patrick@gmail.com");
    }
    @Test
    void findAll__allUsersFound(){
        assertEquals(2, userRepository.findAll().size());
    }
    @Test
    void findById_validId_correctUser(){
        User user3 = new User("Squidward@gmail.com","Squidward Q. Tentacles");
        userRepository.save(user3);
        assertTrue(user3.equals(userRepository.findById("squidward@gmail.com").get()));
        userRepository.deleteById("squidward@gmail.com");
    }

}