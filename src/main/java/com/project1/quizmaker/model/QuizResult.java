package com.project1.quizmaker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QuizResultId;
    @ManyToOne
    @JoinColumn(name = "quiz_taker", nullable = false)
    private User quizTaker;
    @ManyToOne
    @JoinColumn(name = "quiz_taken", nullable = false)
    private Quiz quizTaken;
    private Date date;
    private Integer totalScore;

    public QuizResult(Integer QuizResultId, User quizTaker, Quiz quizTaken, Date date, Integer totalScore) {
        this.QuizResultId = QuizResultId;
        this.quizTaker = quizTaker;
        this.quizTaken = quizTaken;
        this.date = date;
        this.totalScore = totalScore;
    }

    public QuizResult(User quizTaker, Quiz quizTaken, Date date, Integer totalScore) {
        this.quizTaker = quizTaker;
        this.quizTaken = quizTaken;
        this.date = date;
        this.totalScore = totalScore;
    }
}
