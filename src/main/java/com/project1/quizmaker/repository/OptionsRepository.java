package com.project1.quizmaker.repository;

import com.project1.quizmaker.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<Options, Integer> {
}
