package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    List<Choice> findAllByHomeQuestion(Question question);
}
