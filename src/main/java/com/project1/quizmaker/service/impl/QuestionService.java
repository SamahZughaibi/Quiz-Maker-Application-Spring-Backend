package com.project1.quizmaker.service.impl;

import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.model.Question;
import com.project1.quizmaker.repository.ChoiceRepository;
import com.project1.quizmaker.repository.QuestionRepository;
import com.project1.quizmaker.service.interfaces.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ChoiceRepository choiceRepository;

    @Override
    public List<Choice> getAllChoicesByQuestionId(Integer questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question: " + questionId + " not found");
        return choiceRepository.findAllByHomeQuestion(questionOptional.get());
    }

    @Override
    public void addChoiceToQuestion(Choice choice) {
        Optional<Question> questionOptional = questionRepository.findById(choice.getHomeQuestion().getQuestionId());
        if(questionOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question: " + choice.getHomeQuestion().getQuestionId() + " not found");
        choiceRepository.save(choice);
    }

    @Override
    public void updateQuestionText(String questionText, Integer questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question: " + questionId + " not found");
        Question question = questionOptional.get();
        question.setQuestionText(questionText);
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question: " + questionId + " not found");
        questionRepository.deleteById(questionId);
    }
}
