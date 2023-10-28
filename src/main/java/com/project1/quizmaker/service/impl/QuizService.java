package com.project1.quizmaker.service.impl;

import com.project1.quizmaker.model.Question;
import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.repository.QuestionRepository;
import com.project1.quizmaker.repository.QuizRepository;
import com.project1.quizmaker.service.interfaces.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements IQuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestionsByQuizId(Integer quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz: " + quizId + " not found");
        return questionRepository.findAllByHomeQuiz(quizOptional.get());
    }
    public void addQuestionToQuiz(Question question){
        Optional<Quiz> quizOptional = quizRepository.findById(question.getHomeQuiz().getQuizId());
        if (quizOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz: " + question.getHomeQuiz().getQuizId() + " not found");
        questionRepository.save(question);
    }
    public void updateQuizTitle(String title, Integer quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz: " + quizId + " not found");
        Quiz quiz = quizOptional.get();
        quiz.setTitle(title);
        quizRepository.save(quiz);
    }
    public void deleteQuiz(Integer quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz: " + quizId + " not found");
        quizRepository.deleteById(quizId);
    }
}
