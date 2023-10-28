package com.project1.quizmaker.controller.interfaces;

import com.project1.quizmaker.controller.dto.QuizTitleDTO;
import com.project1.quizmaker.model.Question;
import com.project1.quizmaker.model.Quiz;

import java.util.List;

public interface IQuizController {
    public List<Quiz> getAllQuizzes();
    public void deleteQuiz(Integer quizId);
    public List<Question> getAllQuestionsByQuizId(Integer quizId);
    public void addQuestionToQuiz(Question question);
    public void updateQuizTitle(QuizTitleDTO quizTitleDTO, Integer quizId);

}
