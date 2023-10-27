package com.project1.quizmaker.service.impl;

import com.project1.quizmaker.model.*;
import com.project1.quizmaker.repository.*;
import com.project1.quizmaker.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
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

    public User getUserById(String email){
        Optional<User> userOptional = userRepository.findById(email);
        if(userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + email + " not found");
        return userOptional.get();
    }
    public List<Quiz> getAllUserQuizzes(String email) {
        Optional<User> userOptional = userRepository.findById(email);
        if(userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + email + " not found");
        return quizRepository.findAllByOwner(userOptional.get());
    }
    public List<QuizResult> getAllResultsForUser(String email){
        Optional<User> userOptional = userRepository.findById(email);
        if(userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + email + " not found");
        return quizResultRepository.findAllByQuizTaker(userOptional.get());
    }
    public List<QuizResult> getAllResultsForOwnedQuiz(String email, Integer quizId){
        Optional<User> ownerOptional = userRepository.findById(email);

        // 1. check if user exists
        if(ownerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + email + " not found");
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        // 2. check if quiz exists
        if(quizOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz: " + quizId + " not found");

        // 3. check if user is the owner of the requested quiz. A user should not be able to view other users' quiz results
        if(quizOptional.get().getOwner().getEmail() != email) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the owner of quiz:" + quizId);

        return quizResultRepository.findAllByQuizTaken(quizOptional.get());
    }
    public List<Question> getAllUserQuestions(String email){
        Optional<User> userOptional = userRepository.findById(email);
        if(userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + email + " not found");
        List<Quiz> quizList = quizRepository.findAllByOwner(userOptional.get());
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < quizList.size(); i++) {
            // add questions of every quiz to the user's question list
            questionList.addAll(questionRepository.findAllByHomeQuiz(quizList.get(i)));
        }
        return questionList;
    }
    public List<Choice> getAllUserChoices(String email){
        Optional<User> userOptional = userRepository.findById(email);
        if(userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + email + " not found");
        List<Quiz> quizList = quizRepository.findAllByOwner(userOptional.get());
        List<Question> questionList = new ArrayList<>();

        for (int i = 0; i < quizList.size(); i++) {
            // add questions of every quiz to the user's question list
            questionList.addAll(questionRepository.findAllByHomeQuiz(quizList.get(i)));
        }

        List<Choice> choiceList = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            // add choices of every question to the user's choice list
            choiceList.addAll(choiceRepository.findAllByHomeQuestion(questionList.get(i)));
        }

        return choiceList;
    }
    public void addQuizForUser(Quiz quiz){
        //check if the assigned owner exists
        if(quiz.getOwner() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + quiz.getOwner().getEmail() + " not found");
        quizRepository.save(quiz);
    }
    public void updateFullName(String userFullName, String email){
        Optional<User> userOptional = userRepository.findById(email);
        if(userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + email + " not found");
        User user = userOptional.get();
        user.setFullName(userFullName);
        userRepository.save(user);
    }
}
