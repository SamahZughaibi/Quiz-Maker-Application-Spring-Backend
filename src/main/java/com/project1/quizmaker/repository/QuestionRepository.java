package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.Question;
import com.project1.quizmaker.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByHomeQuiz(Quiz quiz);
}
