package com.project1.quizmaker.service.impl;

import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.model.QuizResult;
import com.project1.quizmaker.model.User;
import com.project1.quizmaker.repository.QuizRepository;
import com.project1.quizmaker.repository.QuizResultRepository;
import com.project1.quizmaker.repository.UserRepository;
import com.project1.quizmaker.service.interfaces.IQuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuizResultService implements IQuizResultService {
    @Autowired
    QuizResultRepository quizResultRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void deleteQuizResult(Integer resultId) {
        Optional<QuizResult> quizResultOptionalOptional = quizResultRepository.findById(resultId);
        if(quizResultOptionalOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result: " + resultId + " not found");
        quizResultRepository.deleteById(resultId);
    }

    @Override
    public void addResult(QuizResult quizResult) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizResult.getQuizTaken().getQuizId());
        if (quizOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Quiz: " + quizResult.getQuizTaken().getQuizId() + " not found");

        Optional<User> userOptional = userRepository.findById(quizResult.getQuizTaker().getEmail());
        if(userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "User: " + quizResult.getQuizTaker().getEmail() + " not found");

        quizResult.setDate(new Date()); // register date this result was submitted on
        quizResultRepository.save(quizResult);
    }
}
