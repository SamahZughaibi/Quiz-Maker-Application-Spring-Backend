package com.project1.quizmaker.service.interfaces;

import com.project1.quizmaker.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    public User getUserById(String email);
    public List<Quiz> getAllUserQuizzes(String email);
    public List<QuizResult> getAllResultsForUser(String email);
    public List<QuizResult> getAllResultsForOwnedQuiz(String email, Integer quizId);
    public List<Question> getAllUserQuestions(String email);
    public List<Choice> getAllUserChoices(String email);
    public void addQuizForUser(Quiz quiz);
    public void updateFullName(String userFullName, String email);
}
