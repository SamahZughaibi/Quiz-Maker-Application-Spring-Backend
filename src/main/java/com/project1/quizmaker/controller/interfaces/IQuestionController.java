package com.project1.quizmaker.controller.interfaces;

import com.project1.quizmaker.controller.dto.QuestionTextDTO;
import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.model.Question;

import java.util.List;

public interface IQuestionController {
    public List<Question> getAllQuestions();
    public void deleteQuestion(Integer questionId);
    public List<Choice> getAllChoicesByQuestionId(Integer questionId);
    public void addChoiceToQuestion(Choice choice);
    public void updateQuestionText(QuestionTextDTO quizTextDTO, Integer questionId);
}
