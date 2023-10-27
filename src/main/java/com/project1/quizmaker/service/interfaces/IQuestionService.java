package com.project1.quizmaker.service.interfaces;

import com.project1.quizmaker.model.Choice;

import java.util.List;

public interface IQuestionService {
    List<Choice> getAllChoicesByQuestionId(Integer questionId);

    void addChoiceToQuestion(Choice choice);

    void updateQuestionText(String questionText, Integer questionId);

    void deleteQuestion(Integer questionId);
}
