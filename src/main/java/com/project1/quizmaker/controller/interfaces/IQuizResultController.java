package com.project1.quizmaker.controller.interfaces;

import com.project1.quizmaker.model.QuizResult;

import java.util.List;

public interface IQuizResultController {
    public List<QuizResult> getAllQuizResults();
    public void addQuizForUser(QuizResult quizResult);
    public void deleteQuizResult(Integer resultId);
}
