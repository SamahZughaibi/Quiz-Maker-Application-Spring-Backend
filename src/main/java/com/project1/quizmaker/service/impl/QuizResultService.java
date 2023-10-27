package com.project1.quizmaker.service.impl;

import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.model.QuizResult;
import com.project1.quizmaker.model.User;
import com.project1.quizmaker.repository.QuizResultRepository;
import com.project1.quizmaker.service.interfaces.IQuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class QuizResultService implements IQuizResultService {
    @Autowired
    QuizResultRepository quizResultRepository;
    @Override
    public void deleteQuizResult(Integer resultId) {
        Optional<QuizResult> quizResultOptionalOptional = quizResultRepository.findById(resultId);
        if(quizResultOptionalOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result: " + resultId + " not found");
        quizResultRepository.deleteById(resultId);
    }
}
