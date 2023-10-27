package com.project1.quizmaker.service.interfaces;

import com.project1.quizmaker.model.Question;

import java.util.List;

public interface IQuizService {
    public void deleteQuiz(Integer quizId);
    public List<Question> getAllQuestionsByQuizId(Integer quizId);
    public void addQuestionToQuiz(Question question);
}
