package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query(value = "SELECT sum(points_assigned) FROM question q JOIN quiz qz ON q.for_quiz = qz.quiz_id WHERE q.for_quiz = :quizIdParam", nativeQuery = true)
    Integer calculateScoreForQuiz(@Param("quizIdParam") Integer quizId);
    List<Quiz> findAllByOwner(User owner);

}
