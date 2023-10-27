package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.model.QuizResult;
import com.project1.quizmaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {
    List<QuizResult> findAllByQuizTaker(User quizTaker);
    List<QuizResult> findAllByQuizTaken(Quiz quizTaken);
}
