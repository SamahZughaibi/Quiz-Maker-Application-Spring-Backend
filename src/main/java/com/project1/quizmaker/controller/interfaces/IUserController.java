package com.project1.quizmaker.controller.interfaces;

import com.project1.quizmaker.controller.dto.UserFullNameDTO;
import com.project1.quizmaker.model.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserController {
    List<User> getAllUsers();
    public User getUserById(String email);
    public List<Quiz> getAllUserQuizzes(String email);
    public List<QuizResult> getAllResultsForUser(String email);
    public List<QuizResult> getAllResultsForOwnedQuiz(String email, Integer quizId);
    public List<Question> getAllUserQuestions(String email);
    public List<Choice> getAllUserChoices(String email);
    public void addUser(User user);
    public void addQuizForUser(Quiz quiz);
    public void updateFullName(UserFullNameDTO userFullNameDTO, String email);
}
