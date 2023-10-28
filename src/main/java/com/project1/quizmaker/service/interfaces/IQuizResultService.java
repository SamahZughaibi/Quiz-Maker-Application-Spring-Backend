package com.project1.quizmaker.service.interfaces;

import com.project1.quizmaker.model.QuizResult;

public interface IQuizResultService {
    void deleteQuizResult(Integer resultId);

    void addResult(QuizResult quizResult);
}
